package org.exercises.from_API_to_DTO;

import java.util.List;

public class MediaDTO {

    List<MovieDTO> movie_results;

    @Override
    public String toString() {
        return "MediaDTO{" +
                "movie_results=" + movie_results +
                '}';
    }
}
