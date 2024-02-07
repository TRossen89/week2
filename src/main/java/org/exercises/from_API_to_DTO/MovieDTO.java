package org.exercises.from_API_to_DTO;

import java.time.LocalDate;

public class MovieDTO {
    String overview;
    String release_date;
    transient LocalDate release_date_as_local_date;
    transient String release_year;

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public LocalDate getRelease_date_as_local_date() {
        return release_date_as_local_date;
    }

    public void setRelease_date_as_local_date(LocalDate release_date_as_local_date) {
        this.release_date_as_local_date = release_date_as_local_date;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
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
