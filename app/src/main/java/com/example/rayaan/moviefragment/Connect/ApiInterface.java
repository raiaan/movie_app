package com.example.rayaan.moviefragment.Connect;

import com.example.rayaan.moviefragment.Model.MoviesResponse;
import com.example.rayaan.moviefragment.Model.Review;
import com.example.rayaan.moviefragment.Model.Tailers;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rayaan on 08/08/2016.
 */
public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
    @GET("movie/{id}/videos")
    Call<Tailers> getTailers(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("movie/{id}/reviews")
    Call<Review> getreviews(@Path("id") int id, @Query("api_key") String apiKey);
}
