package com.example.movieapp.data.remote.model

data class MovieListResponse(
    val results: List<MovieResponse> = listOf()
)

data class MovieResponse(
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