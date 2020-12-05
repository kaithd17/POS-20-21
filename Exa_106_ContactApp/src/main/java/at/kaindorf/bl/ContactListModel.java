/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import java.util.List;
import at.kaindorf.pojos.Contact;
import at.kaindorf.pojos.Company;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kainz
 */
public class ContactListModel {

    public void filterContacts(List<Contact> filteredList, List<String> conditionList) {
        filteredList.removeIf(c -> {
            return !((c.getLastname().toUpperCase().contains(conditionList.get(0).toUpperCase()) || c.getFirstname().toUpperCase().contains(conditionList.get(0).toUpperCase()))
                    && c.getCompany().getName().contains(conditionList.get(1))
                    && c.getGender().contains(conditionList.get(2)));
        });
    }

    public Set<Company> getAllCompanies(List<Contact> contactList) {
        Set<Company> companyList = new TreeSet<>();
        for (Contact contact : contactList) {
            if (!(companyList.contains(contact.getCompany()))) {
                companyList.add(contact.getCompany());
            }
        }
        return companyList;
    }

    public void setFavouritesAgain(Set<String> idSet) {

    }
}
