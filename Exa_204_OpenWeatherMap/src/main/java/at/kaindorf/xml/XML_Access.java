/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.xml;
import at.kaindorf.pojos.CurrentWeather;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXB;

/**
 *
 * @author kainz
 */
public class XML_Access {
    private static final String URL_BASE = "https://api.openweathermap.org/data/2.5/weather?appid=30ce4ef9073eaaa91ff5304271824fda";
    
    public static CurrentWeather getCurrentWeather(String searchString, String language) throws MalformedURLException, UnsupportedEncodingException {
        CurrentWeather weather = new CurrentWeather();
        URL url = new URL(URL_BASE + "&q=" + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()) + "&mode=xml" + "&lang=" + language);
        weather = JAXB.unmarshal(url, CurrentWeather.class);
        return weather;
    }
    
    public static void main(String[] args) {
        try {
            CurrentWeather weather;
            weather = XML_Access.getCurrentWeather("Graz", "de");
            System.out.println(weather);
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        }
    }
}
