/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.json;

import java.util.List;
import at.kaindorf.pojos.Movie;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author kainz
 */
public class JSONAccess {

    public static List<Movie> getAllMovies(String searchString, int page) {
        List<Movie> movieList = new ArrayList<>();
        try {
            //first requests
            URL url = new URL("http://www.omdbapi.com/?apikey=e7c841e0&s=" + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()) + "&page=" + page);
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);
            ObjectMapper objectmapper = new ObjectMapper();
            objectmapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //second requests
            URL newURL;
            int counter = 0;
            for (int i = 0; i < node.get("Search").size(); i++) {
                String imdbID = node.get("Search").get(i).get("imdbID").asText();
                newURL = new URL("http://www.omdbapi.com/?apikey=e7c841e0&i=" + URLEncoder.encode(imdbID, StandardCharsets.UTF_8.toString()));
                movieList.add(objectmapper.readValue(newURL, Movie.class));

                //set Id
                counter++;
                movieList.get(i).setId(counter);
            }
            return movieList;
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return movieList;
    }

    public static int getPages(String searchString) {
        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=e7c841e0&s=" + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()));
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);
            int pages = Integer.parseInt(node.get("totalResults").asText());
            int temp = pages;
            pages /= 10;
            temp %= 10;
            if (temp != 0) {
                pages++;
            }
            return pages;
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            List<Movie> movieList = JSONAccess.getAllMovies("Galaxy", 24);

            for (Movie movie : movieList) {
                System.out.println(movie);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getStackTrace());
            System.out.println("No Results");
        }
    }
}
