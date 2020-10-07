/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.io;

import at.kaindorf.beans.Pizza;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
public class IO_Handler {
    
    public static List<Pizza> getAllPizzas(String path) throws FileNotFoundException{
        List<Pizza> pizzaList = new ArrayList<>();
        try{
            pizzaList = new BufferedReader(new FileReader(path)).lines().skip(1).map(Pizza::getPizza).collect(Collectors.toList());
        }catch(IOException ex){
            
        }
        return pizzaList;
    }
}
