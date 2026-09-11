package com.example.movieapp.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.presentation.ui.login.LoginScreen
import com.example.movieapp.presentation.ui.movies.MoviesScreen
import com.example.movieapp.presentation.ui.moviesDetail.MoviesDetailScreen

@Composable
fun AppRootNavigation() {

    val backStack: NavBackStack<NavKey> = rememberNavBackStack(AppRoutes.Login)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {

            entry<AppRoutes.Login> {

                LoginScreen(navigationToMovies = { backStack.navigateAndReplace(AppRoutes.Movies) })
            }

            entry<AppRoutes.Movies> {

                MoviesScreen(onMovieClick = {

                    backStack.navigateTo(
                        AppRoutes.MoviesDetail(
                            MovieDetail(
                                id = it.id,
                                poster_path = it.poster_path,
                                original_title = it.original_title,
                                release_date = it.release_date,
                                overview = it.overview,
                                genres = it.genre_ids
                            )
                        )
                    )
                })
            }

            entry<AppRoutes.MoviesDetail> { route ->


                MoviesDetailScreen(navigateBack = { backStack.back() }, movieDetail = route.movieDetail)
            }
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(Time.DURATION_MILLIS)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(Time.DURATION_MILLIS)
            )
        }, popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(Time.DURATION_MILLIS)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(Time.DURATION_MILLIS)
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(Time.DURATION_MILLIS)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(Time.DURATION_MILLIS)
            )
        }
    )
}

object Time {

    const val DURATION_MILLIS = 300
}