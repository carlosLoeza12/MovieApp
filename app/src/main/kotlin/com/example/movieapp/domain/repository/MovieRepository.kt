package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.ExtrasMovieDetail
import com.example.movieapp.domain.model.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getExtrasMovieDetail(movieId: Int): ExtrasMovieDetail
}