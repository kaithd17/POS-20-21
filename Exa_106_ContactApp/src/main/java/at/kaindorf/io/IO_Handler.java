/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import at.kaindorf.pojos.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author kainz
 */
public class IO_Handler {
    public static List<Contact> getAllContacts (String path){
        try {
            return new ObjectMapper().readValue(new File(path), new TypeReference<List<Contact>>() { });
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
