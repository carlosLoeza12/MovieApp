package com.example.movieapp.data.remote.api

import com.example.movieapp.data.remote.model.MovieListResponse
import com.example.movieapp.domain.model.ExtrasMovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getMovies(@Query("api_key") apiKey: String): MovieListResponse

    @GET("{movie_id}")
    suspend fun getExtrasMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): ExtrasMovieDetail
}