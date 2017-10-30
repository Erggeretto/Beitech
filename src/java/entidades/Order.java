/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Erggeretto
 */
public class Order {
    private int customerId;
    private List<String> productDescription;
    private List<String> price;
    public Order() {
    }

    public Order(int customerId) {
        this.customerId = customerId;
    }

    public Order(Integer customerId, List<String> productDescription, List<String> price) {
        this.customerId = customerId;
        this.productDescription = productDescription;
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(List<String> productDescription) {
        this.productDescription = productDescription;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }
    
    
}
