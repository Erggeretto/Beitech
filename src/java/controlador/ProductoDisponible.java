/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Customer;
import entidades.CustomerProduct;
import entidades.Maximo;
import entidades.Product;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CustomerDAO;
import modelo.CustomerProductDAO;
import modelo.JsonTransformer;
import modelo.ProductDAO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Erggeretto
 */
@Controller
public class ProductoDisponible {
    //Inyeccion de dependencias
    @Autowired
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private CustomerProductDAO customerProductDAO;
    private CustomerProduct customerProduct;
    private String jsonClientes;
    private String jsonProductos;
    private String jsonSalida;
    private JsonTransformer jsonTransformer;
    private final int maximoDisponible = 5;
    String jsonClientesProductos = "";
    
    @RequestMapping(value = "/inventario/disponibles", 
            method = RequestMethod.GET, 
            produces = "application/json")
    public void mostrarClientesProductos(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse){
        try{
            List<Customer> clientes = customerDAO.readAllJPQL();
            List<Product> productos = productDAO.readAllJPQL();
            List<CustomerProduct> clientesProductos = customerProductDAO.readAllJPQL();
            JSONObject jsonPresentacion = new JSONObject();
            jsonClientes = jsonTransformer.toJson(clientes);
            jsonProductos = jsonTransformer.toJson(productos);
            for(int i=0;i<clientesProductos.size();i++){
                jsonClientesProductos += jsonTransformer.toJson(clientesProductos.get(i));
            }
            jsonPresentacion.append("clientes", jsonClientes);
            jsonPresentacion.append("productos", jsonProductos);
            jsonPresentacion.append("clientesproductos", jsonClientesProductos);
            jsonSalida = jsonTransformer.toJson(jsonPresentacion);
            if(clientes.size()>0){
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
    
    @RequestMapping(value = "/inventario/disponibles", 
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public void asignarClientesProductos(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody String jsonEntrada,@RequestParam("idCustomer") int idCustomer){
        try{
            //Se borra las relaciones con el cliente
            customerProductDAO.delete(idCustomer);
            customerProduct = (CustomerProduct)jsonTransformer.fromJson(jsonEntrada, CustomerProduct.class);
            //Se crean nuevas relaciones
            customerProductDAO.create(customerProduct);
            String jsonSalida=jsonTransformer.toJson(customerProduct);
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        customerProduct = customerProductDAO.readById(idCustomer);
        if(!customerProduct.equals(null)){
            String jsonSalida = jsonTransformer.toJson(customerProduct);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try{
                httpServletResponse.getWriter().println(jsonSalida);
            }catch(Exception e){
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else{
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}