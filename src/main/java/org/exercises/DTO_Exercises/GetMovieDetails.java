package org.exercises.DTO_Exercises;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetMovieDetails {
    private List<String> listOfUrls = new ArrayList<>();
    private List<MovieDTO> listOfMovieDTOs = new ArrayList<>();

    public GetMovieDetails() {
    }

    public static void main(String[] args) {

        MovieController movieController = new MovieController();


        // ############# API Data Retrieval #############

        String urlOfTheShawshankRedemption = "https://api.themoviedb.org/3/find/tt0111161?external_source=imdb_id&language=english";

        //getMovieDetails.getResponseBodyById(url, theShawShankRedemptionId);


        // Movie overview - 2 solutions

        // - Solution 1
        MediaDTO media = movieController.convertToMediaDTO(urlOfTheShawshankRedemption);

        MovieDTO movieDTO1 = media.movie_results.get(0);

        System.out.println(movieDTO1.overview);


        // - Solution 2
        JsonObject overviewObject = movieController.getMovieOverviewAsJsonObject(urlOfTheShawshankRedemption);

        MovieDTO movieDT02 = new MovieDTO();

        movieDT02.setOverview(overviewObject.get("overview").getAsString());

        System.out.println(movieDT02.overview);



        // LocalDate
        MovieDTO movieDTO3 = movieController.getMovieDTOWithLocalDate(urlOfTheShawshankRedemption, "imdb_id");

        System.out.println("Release year: " + movieDTO3.release_year +
                "\nRelease date as local date: " + movieDTO3.release_date_as_local_date);







        
        // ############## Adding functionality ##############

        // Creating collection of movies
        List<MovieDTO> listOfMovieDTOs = new ArrayList<>();
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


        // Getting every movie from collection that has a vote average above 8.2
        List<MovieDTO> listOfMoviesByRating = movieController.getByRating(8.5, listOfMovieDTOs);

        System.out.println("########## List of movies with a vote average above 8.5: ########### \n");

        for (MovieDTO mvDTO : listOfMoviesByRating) {
            System.out.println("Title: " + mvDTO.title + "\n" + "Vote average: " + mvDTO.vote_average + "\n--\n");
        }


        // Sorting all movies from collection by date:
        List<MovieDTO> listOfMoviesSortedByReleaseDate = movieController.getSortedByReleaseDate(listOfMovieDTOs);

        System.out.println("\n########### Movies sorted by release date: ########### \n");
        for (MovieDTO movieDTO : listOfMoviesSortedByReleaseDate) {
            System.out.println("Title: " + movieDTO.title + "\n" + "Release date: " + movieDTO.release_date + "\n--\n");
        }


        // Searching for a movie by title
        try{
            MovieDTO movieDTO4 = movieController.searchBytTitle("Pul fiction");
            System.out.println(movieDTO4.title);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Search engine wasn't able to find a movie with that title");
        }





    }



    public List<String> getListOfUrls() {
        return listOfUrls;
    }

    public List<MovieDTO> getListOfMovieDTOs() {
        return listOfMovieDTOs;
    }


}
