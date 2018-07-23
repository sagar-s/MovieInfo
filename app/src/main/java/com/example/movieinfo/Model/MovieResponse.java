package com.example.movieinfo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> results;

    public MovieResponse(List<Movie> reusults) {
        this.results = reusults;

    }

    public List<Movie> getResults() {
        return results;
    }
}
