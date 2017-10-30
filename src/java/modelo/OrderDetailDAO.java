/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.OrderDetail;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erggeretto
 */
@Service
public class OrderDetailDAO {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void create(OrderDetail dto) throws ServicioException{
        em.persist(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void update(OrderDetail dto) throws ServicioException{
        em.merge(dto);
    }
    
    @Transactional(rollbackFor = {ServicioException.class})
    public void delete(int idOrderDetail) throws ServicioException{
        OrderDetail dto = readById(idOrderDetail);
        em.remove(dto);
    }
    
    public OrderDetail readById(int idOrderDetail)throws SecurityException{
        return em.find(OrderDetail.class, idOrderDetail);
    }
}
