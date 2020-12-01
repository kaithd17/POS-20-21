/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import java.util.List;
import at.kaindorf.pojos.Contact;

/**
 *
 * @author kainz
 */
public class ContactListModel {
    
    public List<Contact> setContactToFavourite(List<Contact> contactList, List<Contact> favourites) {
        for (int i = 0; i < contactList.size(); i++) {
            for (int j = 0; j < favourites.size(); j++) {
                if (contactList.get(i).getId() == favourites.get(j).getId()) {
                    contactList.get(i).setFavourite(true);
                }
            }
        }
        return contactList;
    }
}
