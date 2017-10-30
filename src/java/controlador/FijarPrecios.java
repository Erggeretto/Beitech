/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.JsonTransformer;
import modelo.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Erggeretto
 */
@Controller
public class FijarPrecios {
    //Se inyecta una dependencia
    @Autowired
    private ProductDAO productDAO;
    private JsonTransformer jsonTransformer;
    private Product producto;
    
    @RequestMapping(value = "/costo/productos", method = RequestMethod.GET, produces = "application/json")
    public void mostrarProductosPrecios(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        try{
            List<Product> productos = productDAO.readAllJPQL();
            String jsonSalida = jsonTransformer.toJson(productos);
            if(productos.size()>0){
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            }else{
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }catch(Exception ex){
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/costo/precios", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void cambiar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idSeguroMedico") int idSeguroMedico){
        try{
            producto = (Product) jsonTransformer.fromJson(jsonEntrada, Product.class);
            productDAO.update(producto);
            String jsonSalida = jsonTransformer.toJson(producto);
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        }catch(Exception ex){
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
