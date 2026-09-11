package com.example.movieapp.data.remote.model

import com.example.movieapp.domain.model.Movie

fun MovieResponse.toDomainModel(): Movie {
    return Movie(
        id = id,
        poster_path = poster_path,
        adult = adult,
        overview = overview,
        release_date = release_date,
        genre_ids = genre_ids,
        original_title = original_title,
        original_language = original_language,
        title = title,
        backdrop_path = backdrop_path,
        popularity = popularity,
        vote_count = vote_count,
        video = video,
        vote_average = vote_average
    )
}