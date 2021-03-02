/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.xml;

import java.util.List;
import at.kaindorf.pojos.Channel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.graalvm.compiler.core.common.type.TypeReference;

/**
 *
 * @author kainz
 */
public class XMLAccess {
    
    public static Channel getFeeds(String urlStr) throws MalformedURLException, IOException {
            Channel channel = new Channel();
            URL url = new URL(urlStr);
            channel = JAXB.unmarshal(url, Channel.class);
            return channel;
    }
    
    public static void main(String[] args) {
        try {
            Channel channel = XMLAccess.getFeeds("https://www.diepresse.com/rss/home");
            System.out.println(channel);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
