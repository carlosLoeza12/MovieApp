package com.example.movieapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int = -1,
    val poster_path: String = "",
    val adult: Boolean = false,
    val overview: String = "",
    val release_date: String = "",
    val genre_ids: List<Int> = listOf(),
    val original_title: String = "",
    val original_language: String = "",
    val title: String = "",
    val backdrop_path: String = "",
    val popularity: Double = 0.0,
    val vote_count: Int = -1,
    val video: Boolean = false,
    val vote_average: Double = 0.0
)

@Serializable
data class MovieDetail(
    val id: Int = -1,
    val poster_path: String = "",
    val original_title: String = "",
    val release_date: String = "",
    val overview: String = "",
    val genres: List<Int> = listOf()
)

@Serializable
data class ExtrasMovieDetail(
    val id: Int = -1,
    val runtime: Int,
    val genres: List<Genre> = listOf()
)

@Serializable
data class Genre(
    val id: Int = -1,
    val name: String = ""
)