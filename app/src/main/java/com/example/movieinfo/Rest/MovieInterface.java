package com.example.movieinfo.Rest;

import com.example.movieinfo.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie/{user}")
    Call<MovieResponse> getMovies(@Path("user") String user, @Query("api_key") String apiKey);

}
