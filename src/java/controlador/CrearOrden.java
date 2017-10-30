/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Customer;
import entidades.Order;
import entidades.Order1;
import entidades.OrderDetail;
import entidades.Product;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CustomerDAO;
import modelo.JsonTransformer;
import modelo.OrderDAO;
import modelo.OrderDetailDAO;
import modelo.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Erggeretto
 */
@Controller
public class CrearOrden {
    //Se inyecta una dependencia
    @Autowired
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO; 
    private JsonTransformer jsonTransformer;
    private Customer cliente;
    private Product producto;
    private Order orden;
    private OrderDetail ordenDetalle;
    private Order1 order1;
    
    @RequestMapping(value = "/compra/clientesorden", method = RequestMethod.GET, produces = "application/json")
    public void mostrarClientes(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
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
    
    @RequestMapping(value = "/compra/productosorden", method = RequestMethod.POST, produces = "application/json")
    public void mostrarProductosCliente(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse, 
            @RequestParam("idCliente") int idCliente){
        try{
            List<Product> productos = productDAO.readByIdCustomer(idCliente);
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
    
    @RequestMapping(value = "/compra/ordena", method = RequestMethod.POST, produces = "application/json")
    public void mostrarOrden(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse, 
            @RequestBody String jsonEntrada){
        orden = (Order) jsonTransformer.fromJson(jsonEntrada, Order.class);
        if(!orden.equals(null)){
            String jsonSalida = jsonTransformer.toJson(orden);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try{
                httpServletResponse.getWriter().println(jsonSalida);
            }catch(Exception ex){
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else{
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/compra/pago", method = RequestMethod.POST, 
            produces = "application/json", 
            consumes = "application/json")
    public void crear(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse,
            @RequestBody String jsonEntrada,
            @PathVariable("address") String address){
        try{
            ordenDetalle = (OrderDetail) jsonTransformer.fromJson(jsonEntrada, OrderDetail.class);
            order1.setDeliveryAddress(address);
            Date fecha = new Date();
            order1.setDate(fecha);
            orderDAO.create(order1);
            orderDetailDAO.create(ordenDetalle);
            String jsonSalida=jsonTransformer.toJson(order1);
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
