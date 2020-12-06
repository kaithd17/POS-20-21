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
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kainz
 */
public class ContactListModel {

    public void filterContacts(List<Contact> filteredList, List<String> conditionList) {
        filteredList.removeIf(contact -> {
            return !((contact.getLastname().toUpperCase().contains(conditionList.get(0).toUpperCase()) || contact.getFirstname().toUpperCase().contains(conditionList.get(0).toUpperCase()))
                    && contact.getCompany().getName().contains(conditionList.get(1))
                    && contact.getGender().contains(conditionList.get(2)));
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

    public void sortList(List<Contact> filteredList, String condition) {
        switch (condition) {
            case "Name":
                filteredList.sort(Comparator.comparing(Contact::getLastname).thenComparing(Contact::getFirstname));
                break;
            case "Name+Company":
                filteredList.sort(Comparator.comparing(Contact::getCompany).thenComparing(Contact::getLastname).thenComparing(Contact::getFirstname));
                break;
            case "Age":
                filteredList.sort(Comparator.comparing(Contact::getAge));
                break;
            case "NameReverse":
                filteredList.sort(Comparator.comparing(Contact::getLastname).thenComparing(Contact::getFirstname).reversed());
                break;
            case "Name+CompanyReverse":
                filteredList.sort(Comparator.comparing(Contact::getCompany).thenComparing(Contact::getLastname).thenComparing(Contact::getFirstname).reversed());
                break;
            case "AgeReverse":
                filteredList.sort(Comparator.comparing(Contact::getAge).reversed());
                break;
            default:
                filteredList.sort(Comparator.comparing(Contact::getId));
                break;
        }
    }

    public void setFavouritesAgain(Set<String> idSet) {

    }
}
