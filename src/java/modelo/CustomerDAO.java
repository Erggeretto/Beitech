/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Customer;
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
public class CustomerDAO {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void create(Customer dto) throws ServicioException{
        em.persist(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void update(Customer dto) throws ServicioException{
        em.merge(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void delete(int idCustomer) throws ServicioException{
        Customer dto = readById(idCustomer);
        em.remove(dto);
    }
    
    public Customer readById(int idCustomer)throws SecurityException{
        return em.find(Customer.class, idCustomer);
    }
    
    public Customer readByIdJPQL(String idCustomer) throws SecurityException{
        String sql = "SELECT a FROM Customer a WHERE a.customerId = :idCustomer";
        Query q=em.createQuery(sql);
        q.setParameter("idCustomer", idCustomer);
        try{
            return (Customer)q.getSingleResult();            
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Customer> readAllJPQL() throws SecurityException{
        String sql = "SELECT a FROM Customer a";
        Query q =em.createQuery(sql);
        return q.getResultList();
    }
}
