package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.ExtrasMovieDetail
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetExtrasMovieDetailUseCase @Inject constructor(private val repository: MovieRepository)  {

    suspend operator fun invoke(movieId: Int): ExtrasMovieDetail {

        return repository.getExtrasMovieDetail(movieId)
    }
}