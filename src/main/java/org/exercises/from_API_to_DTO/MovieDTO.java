package org.exercises.from_API_to_DTO;

import java.util.List;

public class MovieDTO {
    String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "overview='" + overview + '\'' +
                '}';
    }
}
