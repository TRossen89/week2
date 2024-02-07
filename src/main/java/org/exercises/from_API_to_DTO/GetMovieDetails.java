package org.exercises.from_API_to_DTO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetMovieDetails {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        GetMovieDetails getMovieDetails = new GetMovieDetails();

        String url = "https://api.themoviedb.org/3/find/{external_id}?external_source=imdb_id&language=english";
        String theShawShankRedemptionId = "tt0111161";
        //getMovieDetails.getResponseBodyById(url, theShawShankRedemptionId);


        // Movie overview - 2 solutions

        // - Solution 1
        MediaDTO media = getMovieDetails.convertToMediaDTO(url, theShawShankRedemptionId);

        MovieDTO movieDTO1 = media.movie_results.get(0);

        System.out.println(movieDTO1.overview);


        // - Solution 2
        JsonObject overviewObject = getMovieDetails.getMovieOverviewInJsonObject(url, theShawShankRedemptionId);

        MovieDTO movieDT02 = new MovieDTO();

        movieDT02.setOverview(overviewObject.get("overview").getAsString());

        System.out.println(movieDT02.overview);



        // LocalDate
        MovieDTO movieDTO3 = getMovieDetails.getMovieDTOWithLocalDate(url, theShawShankRedemptionId);

        System.out.println("Release year: " + movieDTO3.release_year +
                "\nRelease date as local date: " + movieDTO3.release_date_as_local_date);

    }

    private MovieDTO getMovieDTOWithLocalDate(String url, String movieId){

        MovieDTO movieDTOToReturn = new MovieDTO();

        MediaDTO mediaDTO = convertToMediaDTO(url, movieId);

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

    private MediaDTO convertToMediaDTO(String url, String movieId){
        String details = getResponseBodyById(url, movieId);
        MediaDTO movieDetails = gson.fromJson(details, MediaDTO.class);

        return movieDetails;
    }


    private JsonObject getMovieOverviewInJsonObject(String url, String movieId){;

        String result = getResponseBodyById(url, movieId);

        JsonElement jsonElement = JsonParser.parseString(result);

        if(jsonElement.isJsonObject()){

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
        }
        else {
            throw new RuntimeException("Not a json object");
        }
    }

    private String getResponseBodyById(String url, String movieId){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url
                        .replace("{external_id}", movieId))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMWZjNjk2MDU3ZmNlZjExMTY3ZjUxY2ZhYzUyNzcxYiIsInN1YiI6IjY1YzIxMzAyOGU4ZDMwMDE3Yjc4YTJmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hzZZ8YzDsNKpPdZMAUNd5VjLQ65blMh9UvHJbCOzc8s")
                .build();

        Response response = null;

        try{
            response = client.newCall(request).execute();
            String result = response.body().string();
            //System.out.println(result);
            return result;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private String getResponseBody(String url){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMWZjNjk2MDU3ZmNlZjExMTY3ZjUxY2ZhYzUyNzcxYiIsInN1YiI6IjY1YzIxMzAyOGU4ZDMwMDE3Yjc4YTJmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hzZZ8YzDsNKpPdZMAUNd5VjLQ65blMh9UvHJbCOzc8s")
                .build();

        Response response = null;

        try{
            response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println(result);
            return result;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
