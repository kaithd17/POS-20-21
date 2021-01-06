/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import java.util.List;
import at.kaindorf.pojos.Movie;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author kainz
 */
public class MovieListModel {

    public void sortList(List<Movie> movieList, String condition) {
        try {
            switch (condition) {
                case "Title":
                    movieList.sort(Comparator.comparing(Movie::getTitle));
                    break;

                case "Realease":
                    movieList.sort(Comparator.comparing(Movie::getDays).reversed());
                    break;

                default:
                    movieList.sort(Comparator.comparing(Movie::getId));
                    break;
            }
        } catch (NullPointerException ex) {
            movieList.sort(Comparator.comparing(Movie::getYear));
        }

    }

    public Set<String> getGenres(List<Movie> movieList) {
        Set<String> genres = new TreeSet<>();
        for (Movie movie : movieList) {
            String[] genreArray = movie.getGenre().split(", ");
            for (String genre : genreArray) {
                genres.add(genre);
            }
        }
        return genres;
    }

    public void filterList(List<Movie> movieList, String condition) {
        if (condition.equals("<None>")) {
            condition = "";
        }
        final String finalCondition = condition;
        movieList.removeIf(m -> !(m.getGenre().contains(finalCondition)));
    }
}
