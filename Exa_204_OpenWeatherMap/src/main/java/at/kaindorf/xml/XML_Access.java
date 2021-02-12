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

    private static final String URL_BASE = "http://api.openweathermap.org/data/2.5/weather?appid=30ce4ef9073eaaa91ff5304271824fda";

    public static CurrentWeather getCurrentWeather(String searchString, String language) throws MalformedURLException, UnsupportedEncodingException {
        language = language.substring(0, 2).toLowerCase();
        CurrentWeather weather = new CurrentWeather();
        URL url = new URL(URL_BASE + "&q=" + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()) + "&mode=xml" + "&lang=" + language + "&units=metric");
        weather = JAXB.unmarshal(url, CurrentWeather.class);
        return weather;
    }
}
