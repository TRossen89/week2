package org.exercises.DTO_Exercises;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieController implements EntityController<MovieDTO> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public MovieDTO getMovieDTOWithLocalDate(String url, String source) throws IndexOutOfBoundsException {

        MovieDTO movieDTOToReturn = new MovieDTO();

        MediaDTO mediaDTO = convertToMediaDTO(url);

        if (source.equals("imdb_id")) {
            movieDTOToReturn = mediaDTO.movie_results.get(0);

        } else if (source.equals("title")) {
            movieDTOToReturn = mediaDTO.results.get(0);
        }

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


    public JsonObject getMovieOverviewAsJsonObject(String url) {

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


    public List<MovieDTO> getByRating(Double rating, List<MovieDTO> listOfMovieDTOs) {

        List<MovieDTO> listOfMoviesByRating = new ArrayList<>();

        // Collection of movies
        for (MovieDTO mvDTO : listOfMovieDTOs) {
            //System.out.println(mvDTO);
            if (mvDTO.vote_average > rating) {
                listOfMoviesByRating.add(mvDTO);
            }
        }
        return listOfMoviesByRating;
    }

    public List<MovieDTO> getSortedByReleaseDate(List<MovieDTO> listOfMovieDTOs) {

        List<MovieDTO> listToReturn;

        listToReturn = listOfMovieDTOs.stream()
                .sorted(Comparator.comparing(mvDTO -> mvDTO.release_date_as_local_date))
                .collect(Collectors.toList());

        return listToReturn;
    }

    public MovieDTO searchBytTitle(String title) throws IndexOutOfBoundsException{

        String url = "https://api.themoviedb.org/3/search/movie?query={movie_title}&include_adult=false&language=en-US&page=1"
                .replace("{movie_title}", title);

            MovieDTO movieDTO = getMovieDTOWithLocalDate(url, "title");

        return movieDTO;
    }

}
