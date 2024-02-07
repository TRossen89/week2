package org.exercises.from_API_to_DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieController implements EntityController<MovieDTO>, Runnable{

    /*private List<MovieDTO> listOfMovieDTOs;

    public void creatingCollectionOfMovies(){
        GetMovieDetails getMovieDetails = new GetMovieDetails();

        listOfMovieDTOs = new ArrayList<>();
        List<String> listOfUrls = new ArrayList<>();

        List<String> movieIds = Arrays.asList("tt0111161", "tt0068646",
                "tt0468569", "tt0071562", "tt0167260",
                "tt0110912", "tt0050083", "tt0060196",
                "tt0109830", "tt0137523", "tt1375666",
                "tt0268978", "tt0119217", "tt0780504",
                "tt0449467");

        for (String ids : movieIds){
            String urlForListOfUrls = "https://api.themoviedb.org/3/find/" + ids
                    + "?external_source=imdb_id&language=english";
            listOfUrls.add(urlForListOfUrls);
        }

        for (String url : listOfUrls){
            listOfMovieDTOs.add(getMovieDetails.getMovieDTOWithLocalDate(url));
        }
    }
     */

    public List<MovieDTO> getByRating(Double rating, List<MovieDTO> listOfMovieDTOs){

        List<MovieDTO> listOfMoviesByRating = new ArrayList<>();

        // Collection of movies
        for (MovieDTO mvDTO : listOfMovieDTOs){
            //System.out.println(mvDTO);
            if(mvDTO.vote_average > rating){
                listOfMoviesByRating.add(mvDTO);
            }
        }
        return listOfMoviesByRating;
    }

    public List<MovieDTO> getSortedByReleaseDate (List<MovieDTO> listOfMovieDTOs){

        List<MovieDTO> listToReturn = new ArrayList<>();


        listToReturn = listOfMovieDTOs.stream()
                .sorted(Comparator.comparing(mvDTO -> mvDTO.release_date_as_local_date))
                .collect(Collectors.toList());

        return listToReturn;
    }

    @Override
    public void run() {

    }
}
