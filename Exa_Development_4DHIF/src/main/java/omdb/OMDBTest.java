/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kainz
 */
public class OMDBTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=e7c841e0&t=Star%20Wars");
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
            System.out.println(json);
            //System.out.println(node.get("Search").get(0));
            //System.out.println(node.get("Ratings").size());
            //System.out.println(node.get("Ratings").get(0).get("Value"));
            //System.out.println(node.toPrettyString());
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
