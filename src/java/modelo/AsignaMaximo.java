/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import entidades.Maximo;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Erggeretto
 */
public class AsignaMaximo {
    public int getMaximo() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("maximo.json");
        Maximo max = objectMapper.readValue(file, Maximo.class);
        return max.getMaximo();
    }
}
