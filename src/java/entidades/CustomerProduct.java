/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Erggeretto
 */
public class CustomerProduct {
    private Integer customerId;
    private Integer productId;
    public CustomerProduct() {
    }

    public CustomerProduct(Integer customerId) {
        this.customerId = customerId;
    }

    public CustomerProduct(Integer customerId, Integer productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.productId = customerId;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
