/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.CustomerProduct;
import entidades.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erggeretto
 */
public class CustomerProductDAO {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void create(CustomerProduct dto) throws ServicioException{
        em.persist(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void update(CustomerProduct dto) throws ServicioException{
        em.merge(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void delete(int idCustomer) throws ServicioException{
        List<CustomerProduct> dto = readByIdJPQL(idCustomer);
        em.remove(dto);
    }
    
    public CustomerProduct readById(int idCustomer)throws SecurityException{
        return em.find(CustomerProduct.class, idCustomer);
    }
    
    public List<CustomerProduct> readByIdJPQL(int idCustomer) throws SecurityException{
        String sql = "SELECT a FROM CustomerProduct a WHERE a.customerId = :idCustomer";
        Query q=em.createQuery(sql);
        q.setParameter("idCustomer", idCustomer);
        return q.getResultList();
    }
    
    public List<CustomerProduct> readAllJPQL() throws SecurityException{
        String sql = "SELECT a FROM CustomerProduct a";
        Query q =em.createQuery(sql);
        return q.getResultList();
    }
}
