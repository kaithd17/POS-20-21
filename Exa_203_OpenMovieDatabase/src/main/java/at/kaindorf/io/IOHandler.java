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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
public class IOHandler {
    
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    
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
            movieList = objectmapper.convertValue(node.get("Search"), new TypeReference<List<Movie>>() {
            });

            //second requests
            URL newURL;
            String[] ratings;
            int counter = 0;
            for (Movie movie : movieList) {
                counter++;
                newURL = new URL("http://www.omdbapi.com/?apikey=e7c841e0&i=" + URLEncoder.encode(movie.getImdbID(), StandardCharsets.UTF_8.toString()));
                node = mapper.readTree(newURL);
                movie.setId(counter);
                movie.setTitle(node.get("Title") != null ? node.get("Title").asText() : "");
                movie.setYear(node.get("Year") != null ? node.get("Year").asText() : "");
                movie.setRated(node.get("Rated") != null ? node.get("Rated").asText() : "");
                //movie.setReleased(node.get("Released") != null ? node.get("Released").asText() : "");
                movie.setRuntime(node.get("Runtime") != null ? node.get("Runtime").asText() : "");
                movie.setGenre(node.get("Genre") != null ? node.get("Genre").asText() : "");
                movie.setDirector(node.get("Director") != null ? node.get("Director").asText() : "");
                movie.setWriter(node.get("Writer") != null ? node.get("Writer").asText() : "");
                movie.setActors(node.get("Actors") != null ? node.get("Actors").asText() : "");
                movie.setPlot(node.get("Plot") != null ? node.get("Plot").asText() : "");
                movie.setLanguage(node.get("Language") != null ? node.get("Language").asText() : "");
                movie.setCountry(node.get("Country") != null ? node.get("Country").asText() : "");
                movie.setAwards(node.get("Awards") != null ? node.get("Awards").asText() : "");
                movie.setPoster(node.get("Poster") != null ? node.get("Poster").asText() : "");
                movie.setMetascore(node.get("Metascore") != null ? node.get("Metascore").asText() : "");
                movie.setImdbRating(node.get("imdbRating") != null ? node.get("imdbRating").asText() : "");
                movie.setImdbVotes(node.get("imdbVotes") != null ? node.get("imdbVotes").asText() : "");
                movie.setType(node.get("Type") != null ? node.get("Type").asText() : "");
                movie.setDvd(node.get("DVD") != null ? node.get("DVD").asText() : "");
                movie.setBoxOffice(node.get("BoxOffice") != null ? node.get("BoxOffice").asText() : "");
                movie.setProduction(node.get("Production") != null ? node.get("Production").asText() : "");
                movie.setWebsite(node.get("Website") != null ? node.get("Website").asText() : "");

                //get ratings
                ratings = new String[node.get("Ratings").size()];
                for (int i = 0; i < ratings.length; i++) {
                    ratings[i] = node.get("Ratings").get(i).get("Source").asText() + ";";
                    ratings[i] += node.get("Ratings").get(i).get("Value").asText();
                }
                movie.setRatings(ratings);

                //get release date
                String relaese = node.get("Released") != null ? node.get("Released").asText() : "";
                LocalDate released = LocalDate.of(Integer.parseInt(relaese.split(" ")[2]), IOHandler.convertMonth(relaese.split(" ")[1]), Integer.parseInt(relaese.split(" ")[0]));
                movie.setReleased(released);
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
    
    public static int convertMonth(String nameOfMonth) {
        switch (nameOfMonth) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 0;
        }
    }
    
    public static void main(String[] args) {
        try {
            List<Movie> movieList = IOHandler.getAllMovies("Star Wars", 1);
//            for (Movie movie : movieList) {
//                System.out.println(movie.getId());
//            }
            
            List<Movie> movies = movieList.stream().map(m -> m.clone()).collect(Collectors.toList());
            //            List<Contact> sessionContactList = contactList.stream().map(c -> c.clone()).collect(Collectors.toList());
            
            movies.get(0).setTitle("Bitte geh");
            System.out.println(movieList.get(0).getTitle());
            System.out.println(movies.get(0).getTitle());
//            int pages = IOHandler.getPages("Star Wars");
//            System.out.println(pages);
        } catch (NullPointerException ex) {
            System.out.println(ex.getStackTrace());
            System.out.println("No Results");
        }
    }
}
