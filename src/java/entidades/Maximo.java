/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

/**
 *
 * @author Erggeretto
 */

public class Maximo {
    private int maximoDisponible = 5;
    
    public Maximo() {        
    }
    
    public Maximo(int maximoDisponible) {
        this.maximoDisponible = maximoDisponible;
    }
    
    public int getMaximo() {
       return maximoDisponible;
    }

    public void setMaximo(int maximo) {
        this.maximoDisponible = maximo;
    }    
 }
