package com.example.movieapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.example.movieapp.domain.model.MovieDetail
import kotlinx.serialization.Serializable

sealed class AppRoutes: NavKey {

    @Serializable
    data object Login: AppRoutes()

    @Serializable
    data object Movies: AppRoutes()

    @Serializable
    data class MoviesDetail(val movieDetail: MovieDetail): AppRoutes()
}
