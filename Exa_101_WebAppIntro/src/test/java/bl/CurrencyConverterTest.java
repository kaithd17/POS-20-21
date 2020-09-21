/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kainz
 */
public class CurrencyConverterTest {
    
    public CurrencyConverterTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of gerCurrencyNameFromIdx method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testConvert() {
        System.out.println("convert");
        float value = 1.0F;
        int currencyIndex = 0;
        float expResult = 1.62F;
        float result = CurrencyConverter.convert(value, currencyIndex);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getname method, of class Currency.
     */
    @org.junit.jupiter.api.Test
    public void testGetname() {
        System.out.println("getname");
        int index = -3;
        String expResult = "Dollar";
        try{
        String result = CurrencyConverter.getname(index);
        fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("index not supported", e.getMessage());
        }
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
