package org.exercises.from_API_to_DTO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetMovieDetails {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<String> listOfUrls = new ArrayList<>();
    private List<MovieDTO> listOfMovieDTOs = new ArrayList<>();

    String url;

    public GetMovieDetails() {
    }

    public GetMovieDetails(String url) {
        this.url = url;

    }

    public static void main(String[] args) {

        GetMovieDetails getMovieDetails = new GetMovieDetails();


        // ############# API Data Retrieval #############

        String urlOfTheShawshankRedemption = "https://api.themoviedb.org/3/find/tt0111161?external_source=imdb_id&language=english";

        //getMovieDetails.getResponseBodyById(url, theShawShankRedemptionId);


        // Movie overview - 2 solutions

        // - Solution 1
        MediaDTO media = getMovieDetails.convertToMediaDTO(urlOfTheShawshankRedemption);

        MovieDTO movieDTO1 = media.movie_results.get(0);

        System.out.println(movieDTO1.overview);


        // - Solution 2
        JsonObject overviewObject = getMovieDetails.getMovieOverviewInJsonObject(urlOfTheShawshankRedemption);

        MovieDTO movieDT02 = new MovieDTO();

        movieDT02.setOverview(overviewObject.get("overview").getAsString());

        System.out.println(movieDT02.overview);


        // LocalDate
        MovieDTO movieDTO3 = getMovieDetails.getMovieDTOWithLocalDate(urlOfTheShawshankRedemption);

        System.out.println("Release year: " + movieDTO3.release_year +
                "\nRelease date as local date: " + movieDTO3.release_date_as_local_date);


        // ############## Adding functionality ##############

        MovieController movieController = new MovieController();

        // Creating collection of movies

        // (There is only 15 different movies. I've repeated them several times
        // because I wanted to try and see if ExecutorService could optimize the process of retrieving
        // the movieDetails from TMDB)

        List<MovieDTO> listOfMovieDTOs = new ArrayList<>();
        List<String> listOfUrls = new ArrayList<>();

        List<String> movieIds = Arrays.asList("tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467", "tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467","tt0111161", "tt0068646",
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
            listOfMovieDTOs.add(getMovieDetails.getMovieDTOWithLocalDate(url));
        }


        //ExecutorService executorService = Executors.newCachedThreadPool();

/*
        for (String url : listOfUrls) {
            GetMovieDetails getMovieDetails1 = new GetMovieDetails(url);

            try {
                Callable task = new Callable() {
                    @Override
                    public Object call() throws Exception {
                        MovieDTO movieDTOToReturn;

                        MediaDTO mediaDTO = getMovieDetails1.convertToMediaDTO(url);

                        movieDTOToReturn = mediaDTO.movie_results.get(0);

                        // Getting release_date as LocalDate
                        String release_date = movieDTOToReturn.release_date;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                        LocalDate localDateOfMovie = LocalDate.parse(release_date, formatter);

                        // Getting release year as a String
                        String release_year = release_date.substring(0, 4);

                        // Setting release_date_as_local_date and release_year
                        movieDTOToReturn.setRelease_date_as_local_date(localDateOfMovie);
                        movieDTOToReturn.setRelease_year(release_year);

                        return movieDTOToReturn;
                    }
                };

                Future<MovieDTO> future = executorService.submit(task);
                listOfMovieDTOs.add(future.get());


            } catch (Exception e) {
                System.out.println(e);
            }
        }

        executorService.shutdown();

 */





        // Getting every movie from collection that has a vote average above 8.2
        List<MovieDTO> listOfMoviesByRating = movieController.getByRating(8.5, listOfMovieDTOs);

        System.out.println("########## List of movies with a vote average above 8.2: ########### \n");

        for (MovieDTO mvDTO : listOfMoviesByRating) {
            System.out.println("Title: " + mvDTO.title + "\n" + "Vote average: " + mvDTO.vote_average + "\n--\n");
        }


        // Sorting all movies from collection by date:
        List<MovieDTO> listOfMoviesSortedByReleaseDate = movieController.getSortedByReleaseDate(listOfMovieDTOs);

        System.out.println("\n########### Movies sorted by release date: ########### \n");
        for (MovieDTO movieDTO : listOfMoviesSortedByReleaseDate) {
            System.out.println("Title: " + movieDTO.title + "\n" + "Release date: " + movieDTO.release_date + "\n--\n");
        }



    }

    public MovieDTO getMovieDTOWithLocalDate(String url) {

        MovieDTO movieDTOToReturn;

        MediaDTO mediaDTO = convertToMediaDTO(url);

        movieDTOToReturn = mediaDTO.movie_results.get(0);

        // Getting release_date as LocalDate
        String release_date = movieDTOToReturn.release_date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localDateOfMovie = LocalDate.parse(release_date, formatter);

        // Getting release year as a String
        String release_year = release_date.substring(0, 4);

        // Setting release_date_as_local_date and release_year
        movieDTOToReturn.setRelease_date_as_local_date(localDateOfMovie);
        movieDTOToReturn.setRelease_year(release_year);

        return movieDTOToReturn;
    }

    public MediaDTO convertToMediaDTO(String url) {
        String details = getResponseBody(url);
        MediaDTO movieDetails = gson.fromJson(details, MediaDTO.class);

        return movieDetails;
    }


    private JsonObject getMovieOverviewInJsonObject(String url) {
        ;

        String result = getResponseBody(url);

        JsonElement jsonElement = JsonParser.parseString(result);

        if (jsonElement.isJsonObject()) {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject jsonObjectToReturn = new JsonObject();
            String overview = jsonObject
                    .getAsJsonArray("movie_results")
                    .get(0)
                    .getAsJsonObject()
                    .get("overview")
                    .getAsString();

            jsonObjectToReturn.addProperty("overview", overview);
            return jsonObjectToReturn;
        } else {
            throw new RuntimeException("Not a json object");
        }
    }

    private String getResponseBodyById(String url, String movieId) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url
                        .replace("{external_id}", movieId))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMWZjNjk2MDU3ZmNlZjExMTY3ZjUxY2ZhYzUyNzcxYiIsInN1YiI6IjY1YzIxMzAyOGU4ZDMwMDE3Yjc4YTJmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hzZZ8YzDsNKpPdZMAUNd5VjLQ65blMh9UvHJbCOzc8s")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            String result = response.body().string();
            //System.out.println(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponseBody(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMWZjNjk2MDU3ZmNlZjExMTY3ZjUxY2ZhYzUyNzcxYiIsInN1YiI6IjY1YzIxMzAyOGU4ZDMwMDE3Yjc4YTJmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hzZZ8YzDsNKpPdZMAUNd5VjLQ65blMh9UvHJbCOzc8s")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            String result = response.body().string();
            //System.out.println(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getListOfUrls() {
        return listOfUrls;
    }

    public List<MovieDTO> getListOfMovieDTOs() {
        return listOfMovieDTOs;
    }


}
