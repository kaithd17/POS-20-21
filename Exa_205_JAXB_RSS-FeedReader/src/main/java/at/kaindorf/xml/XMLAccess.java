/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.xml;

import at.kaindorf.pojos.Rss;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXB;

/**
 *
 * @author kainz
 */
public class XMLAccess {
    
    public static Rss getFeeds(String urlStr) throws MalformedURLException, IOException {
            Rss rssobject = new Rss();
            URL url = new URL(urlStr);
            rssobject = JAXB.unmarshal(url, Rss.class);
            return rssobject;
    }
    
    public static void main(String[] args) {
        try {
            Rss rssobject = XMLAccess.getFeeds("https://www.diepresse.com/rss/home");
            System.out.println(rssobject);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
