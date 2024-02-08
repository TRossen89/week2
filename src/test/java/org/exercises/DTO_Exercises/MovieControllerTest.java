package org.exercises.DTO_Exercises;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieControllerTest {

    List<MovieDTO> listOfMovieDTOs;

    MovieController movieController;

    @BeforeEach
    void setup(){

        movieController = new MovieController();

        listOfMovieDTOs = new ArrayList<>();
        List<String> listOfUrls = new ArrayList<>();

        List<String> movieIds = Arrays.asList("tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467");

        for (String ids : movieIds) {

            String urlForListOfUrls = "https://api.themoviedb.org/3/find/" + ids
                    + "?external_source=imdb_id&language=english";

            listOfUrls.add(urlForListOfUrls);
        }


        for (String url : listOfUrls){
            listOfMovieDTOs.add(movieController.getMovieDTOWithLocalDate(url, "imdb_id"));
        }

    }


    @Test
    void searchByTitle(){

        // Arrange
        List<String> actual = new ArrayList<>();

        List<String> expected = Arrays.asList("The Shawshank Redemption",
                "The Godfather",
                "The Dark Knight",
                "The Godfather Part II",
                "The Lord of the Rings: The Return of the King",
                "Pulp Fiction",
                "12 Angry Men",
                "The Good, the Bad and the Ugly",
                "Forrest Gump",
                "Fight Club",
                "Inception");

        // Act
        for (String movie : expected){
            MovieDTO movieDTO = movieController.searchBytTitle(movie);
            String movieTitleRetrieved = movieDTO.title;
            actual.add(movieTitleRetrieved);
        }

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getByRating(){

        // Arrange
        List<String> actualMovieList = new ArrayList<>();

        List<String> expectedMovies = Arrays.asList(
                "The Shawshank Redemption",
                "The Dark Knight",
                "The Godfather",
                "The Godfather Part II",
                "12 Angry Men");


        // Act
        List<MovieDTO> ListOfMovieDTOsByRating = movieController.getByRating(8.5, listOfMovieDTOs);

        for (MovieDTO movieDTO : ListOfMovieDTOsByRating){
            actualMovieList.add(movieDTO.title);
        }


        // Assert

        int expected = 5;
        int actual = 0;

        for(String actualMovieTitle : actualMovieList){
            for(String expectedMovieTitle : expectedMovies){
                if(actualMovieTitle.equals(expectedMovieTitle)){
                    actual++;
                }
            }
        }

        assertEquals(expected, actual);

    }
}
