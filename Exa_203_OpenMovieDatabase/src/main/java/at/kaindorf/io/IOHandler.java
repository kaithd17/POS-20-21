/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.io;

import java.util.List;
import at.kaindorf.pojos.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kainz
 */
public class IOHandler {

    public static List<Movie> getAllMovies(String searchString) {
        try {
            //first requests
            URL url = new URL("http://www.omdbapi.com/?apikey=e7c841e0&s=" + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()));
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);
            ObjectMapper objectmapper = new ObjectMapper();
            objectmapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Movie> movieList = objectmapper.convertValue(node.get("Search"), new TypeReference<List<Movie>>() {
            });

            //second requests
            URL newURL;
            String[] ratings;
            for (Movie movie : movieList) {
                newURL = new URL("http://www.omdbapi.com/?apikey=e7c841e0&i=" + URLEncoder.encode(movie.getImdbID(), StandardCharsets.UTF_8.toString()));
                node = mapper.readTree(newURL);
                movie.setTitle(node.get("Title").asText());
                movie.setYear(node.get("Year").asText());
                movie.setRated(node.get("Rated").asText());
                movie.setReleased(node.get("Released").asText());
                movie.setRuntime(node.get("Runtime").asText());
                movie.setGenre(node.get("Genre").asText());
                movie.setDirector(node.get("Director").asText());
                movie.setWriter(node.get("Writer").asText());
                movie.setActors(node.get("Actors").asText());
                movie.setPlot(node.get("Plot").asText());
                movie.setLanguage(node.get("Language").asText());
                movie.setCountry(node.get("Country").asText());
                movie.setAwards(node.get("Awards").asText());
                movie.setPoster(node.get("Poster").asText());
                movie.setMetascore(node.get("Metascore").asText());
                movie.setImdbRating(node.get("imdbRating").asText());
                movie.setImdbVotes(node.get("imdbVotes").asText());
                movie.setType(node.get("Type").asText());
                movie.setDvd(node.get("DVD").asText());
                movie.setBoxOffice(node.get("BoxOffice").asText());
                movie.setProduction(node.get("Production").asText());
                movie.setWebsite(node.get("Website").asText());

                ratings = new String[node.get("Ratings").size()];
                for (int i = 0; i < ratings.length; i++) {
                    ratings[i] = node.get("Ratings").get(i).get("Source").asText() + ";";
                    ratings[i] += node.get("Ratings").get(i).get("Value").asText();
                }
                movie.setRatings(ratings);
            }
            return movieList;
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
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
            List<Movie> movieList = IOHandler.getAllMovies("Star Wars");
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
            int pages = IOHandler.getPages("Star Wars");
            System.out.println(pages);
        } catch (NullPointerException ex) {
            System.out.println("No Results");
        }
    }
}
