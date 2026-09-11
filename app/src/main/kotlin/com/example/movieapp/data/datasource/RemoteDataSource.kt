package com.example.movieapp.data.datasource

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.api.ApiService
import com.example.movieapp.data.remote.model.MovieListResponse
import com.example.movieapp.domain.model.ExtrasMovieDetail
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovies(): MovieListResponse {

        return apiService.getMovies(apiKey = BuildConfig.MOVIE_API_KEY)
    }

    suspend fun getExtrasMovieDetail(movieId: Int): ExtrasMovieDetail {

        return apiService.getExtrasMovieDetail(
            movieId = movieId,
            apiKey = BuildConfig.MOVIE_API_KEY
        )
    }
}