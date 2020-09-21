/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author kainz
 */
public class CurrencyConverter {
    private static float[] CHANGE = {1.19F, 0.92F, 8.02F};    
    private static String[] names ={"Dollar","Pound","Yuan"};


    public static float convert(float value, int currencyIndex){
        return value*CHANGE[currencyIndex];
    }

    public static String getname(int index){
        if(index<0 || index>2){
            throw new IllegalArgumentException("index not supported");
        }
        return names[index];
    }
}
