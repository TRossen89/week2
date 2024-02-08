package org.exercises.DTO_Exercises;

import java.time.LocalDate;

public class MovieDTO {
    String title;
    String overview;
    String release_date;
    Double vote_average;
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

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_average=" + vote_average +
                ", release_date_as_local_date=" + release_date_as_local_date +
                ", release_year='" + release_year + '\'' +
                '}';
    }
}
