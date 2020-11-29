/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import at.kaindorf.pojos.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import at.kaindorf.pojos.Company;
import java.util.ArrayList;

/**
 *
 * @author kainz
 */
public class JSONAccess {
    public static List<Contact> getAllContacts (String path){
        try {
            List<Company> companyList = new ArrayList<>();
            List<Contact> contactList = new ObjectMapper().readValue(new File(path), new TypeReference<List<Contact>>() { });
            
            for (Contact contact : contactList) {
                contact.setFavourite(false);
                if (!companyList.contains(contact.getCompany()) ) {
                    companyList.add(contact.getCompany());
                    contact.getCompany().setContacts(new ArrayList<Contact>(){{ add(contact); }});
                    continue;
                }
                Company company = companyList.get(companyList.indexOf(contact.getCompany()));
                contact.setCompany(company);
                company.getContacts().add(contact);
            }
            
            return contactList;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}