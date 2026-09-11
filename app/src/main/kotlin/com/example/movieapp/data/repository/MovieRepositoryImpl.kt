package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.data.remote.model.toDomainModel
import com.example.movieapp.domain.model.ExtrasMovieDetail
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {

        return remoteDataSource.getMovies().results.map {

            it.toDomainModel()
        }
    }

    override suspend fun getExtrasMovieDetail(movieId: Int): ExtrasMovieDetail {

        return remoteDataSource.getExtrasMovieDetail(movieId)
    }
}