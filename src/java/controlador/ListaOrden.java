/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Customer;
import entidades.Order1;
import entidades.OrderDetail;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CustomerDAO;
import modelo.JsonTransformer;
import modelo.OrderDAO;
import modelo.OrderDetailDAO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Erggeretto
 */
@Controller
public class ListaOrden {
    @Autowired
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private CustomerDAO customerDAO;    
    private JsonTransformer jsonTransformer;
    private String jsonSalida;
    private String jsonOrdenes;
    private String jsonDetalles;
    private OrderDetail ordenDetalle;
    private Order1 order1;
    
    @RequestMapping(value = "/historico/listado", 
            method = RequestMethod.GET, 
            produces = "application/json")
    public void mostrarClientes(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse){
        try{
            List<Customer> clientes = customerDAO.readAllJPQL();
            String jsonSalida = jsonTransformer.toJson(clientes);
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
    
    @RequestMapping(value = "/historico/listandoorden",
            method = RequestMethod.GET,
            produces = "application/json")
    public void mostrarOrdenes(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse, 
            @RequestParam("idCustomer") int idCustomer,
            @RequestParam("fechaIni") String fechaIni,
            @RequestParam("fechaFin") String fechaFin) throws ParseException{
        try{
            List<Order1> ordenes = orderDAO.readByIdAndDate(idCustomer,fechaIni,fechaFin);
            JSONObject jsonPresentacion = new JSONObject();
            jsonOrdenes = jsonTransformer.toJson(ordenes);
            for(int i=0;i<ordenes.size();i++){
                List<OrderDetail> detalles = (List<OrderDetail>) orderDAO.readById(ordenes.get(i).getOrderId());
                jsonDetalles += jsonTransformer.toJson(detalles);
            }
            jsonPresentacion.append("ordenes", jsonOrdenes);
            jsonPresentacion.append("detalles", jsonDetalles);
            jsonSalida = jsonTransformer.toJson(jsonPresentacion);
            if(ordenes.size()>0){
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
}