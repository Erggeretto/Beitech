/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Product;
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
public class ProductDAO {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void create(Product dto) throws ServicioException{
        em.persist(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void update(Product dto) throws ServicioException{
        em.merge(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void delete(int idProduct) throws ServicioException{
        Product dto = readById(idProduct);
        em.remove(dto);
    }
    
    public Product readById(int idProduct)throws SecurityException{
        return em.find(Product.class, idProduct);
    }
    
    public Product readByIdJPQL(int idProduct) throws SecurityException{
        String sql = "SELECT a FROM Customer a WHERE a.productId = :idProduct";
        Query q=em.createQuery(sql);
        q.setParameter("idProduct", idProduct);
        try{
            return (Product)q.getSingleResult();            
        }catch(Exception e){
            return null;
        }
    }
    
    public List<Product> readAllJPQL() throws SecurityException{
        String sql = "SELECT a FROM Product a";
        Query q =em.createQuery(sql);
        return q.getResultList();
    }
    
    public List<Product> readByIdCustomer(int idCustomer) throws SecurityException{
        em.find(Product.class, idCustomer);
        String sql = "SELECT a FROM Product a WHERE a.customerId = :idCustomer";
        Query q =em.createQuery(sql);
        q.setParameter("idCustomer", idCustomer);
        try{
            return q.getResultList();
        }catch(Exception e){
            return null;
        }        
    }
}
