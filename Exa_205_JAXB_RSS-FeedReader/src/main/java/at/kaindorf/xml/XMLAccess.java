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

    public static final String BASE_URL_Presse = "https://www.diepresse.com/rss/";

    public static Rss getFeeds(String urlStr) throws MalformedURLException, IOException {
        //String urlStr = BASE_URL_Presse + urlPart;
        Rss rssobject = new Rss();
        URL url = new URL(urlStr);
        rssobject = JAXB.unmarshal(url, Rss.class);
        for (int i = 0; i < rssobject.getChannel().getItemList().size(); i++) {
            rssobject.getChannel().getItemList().get(i).setId(i);
            rssobject.getChannel().getItemList().get(i).setRead(false);
        }
        return rssobject;
    }

}
