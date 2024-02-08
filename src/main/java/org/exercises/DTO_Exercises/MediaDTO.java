package org.exercises.DTO_Exercises;

import java.util.List;

public class MediaDTO {

    List<MovieDTO> movie_results;

    List<MovieDTO> results;

    @Override
    public String toString() {
        return "MediaDTO{" +
                "movie_results=" + movie_results +
                ", results=" + results +
                '}';
    }
}
