package org.exercises.from_API_to_DTO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.*;
import lombok.*;

import java.io.IOException;

public class GetMovieDetails {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        GetMovieDetails getMovieDetails = new GetMovieDetails();
        String url = "https://api.themoviedb.org/3/find/{external_id}?external_source=imdb_id&language=english";

        String theShawShankRedemptionId = "tt0111161";
        //getMovieDetails.getResponseBody(url);

        // Test comment


        JsonObject overviewObject = getMovieDetails.getMovieOverview(url, theShawShankRedemptionId);

        MovieDTO properMovieDT0 = new MovieDTO();

        properMovieDT0.setOverview(overviewObject.get("overview").getAsString());

        System.out.println(properMovieDT0.overview);



        /* System.out.println("Overview object: " + overviewObject.get("overview"));

        MovieDTO movieDTO = getMovieDetails.getMovieDetails(url, theShawShankRedemptionId);

        System.out.println(movieDTO.overview);

         */


    }

    private MovieDTO getMovieDetails(String url, String movieId){
        String details = getResponseBody(url, movieId);
        MovieDTO movieDetails = gson.fromJson(details, MovieDTO.class);
        return movieDetails;
    }

    private JsonObject getMovieOverview (String url, String movieId){;

        String result = getResponseBody(url, movieId);

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
    private String getResponseBody(String url, String movieId){
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
            System.out.println(result);
            return result;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
