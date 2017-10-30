/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Customer;
import entidades.Order1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erggeretto
 */
@Service
public class OrderDAO {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void create(Order1 dto) throws ServicioException{
        em.persist(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void update(Order1 dto) throws ServicioException{
        em.merge(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void delete(int idOrder) throws ServicioException{
        Order1 dto = readById(idOrder);
        em.remove(dto);
    }
    
    public Order1 readById(int idOrder)throws SecurityException{
        return em.find(Order1.class, idOrder);
    }
    
    public List<Order1> readByIdAndDate(int idCustomer,String fechaIni, String fechaFin) throws ParseException{
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicial;
        Date fechaFinal;
        if(fechaIni.equals("")){
            fechaInicial = null;
        }else{
            fechaInicial = formateador.parse(fechaIni);
        }
        if(fechaFin.equals("")){
            fechaFinal = null;
        }else{
            fechaFinal = formateador.parse(fechaFin);
        }
        String sql = "SELECT a FROM Order a WHERE a.customerId = :idCustomer ";
        if(!fechaInicial.equals(null)){
            sql += "AND a.date >= :fechaInicial ";
        }
        if(!fechaFinal.equals(null)){
            sql += "AND a.date <= :fechaFinal ";
        }
        Query q=em.createQuery(sql);
        q.setParameter("idCustomer", idCustomer);
        if(!fechaIni.equals("")){
            q.setParameter("fechaInicial", fechaInicial);
        }
        if(!fechaFin.equals("")){
            q.setParameter("fechaFinal", fechaFinal);
        }
        try{
            return q.getResultList();            
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Order1> readByIdAndMonth(int idCustomer) throws ParseException{
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = GregorianCalendar.getInstance();
        Date haceMes = new Date();
        calendario.setTime(haceMes);
        calendario.add(Calendar.MONTH, -1);
        haceMes = calendario.getTime();
        String fechaInicial = formateador.format(haceMes);
        String sql = "SELECT a FROM Order a WHERE a.customerId = :idCustomer ";
        sql += "AND a.date >= :fechaInicial ";
        Query q=em.createQuery(sql);
        q.setParameter("idCustomer", idCustomer);
        q.setParameter("fechaInicial", fechaInicial);
        try{
            return q.getResultList();            
        }catch(Exception e){
            return null;
        }
    }
}
