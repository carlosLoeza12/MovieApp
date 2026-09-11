package com.example.movieapp.presentation.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.R
import com.example.movieapp.core.components.Loading
import com.example.movieapp.core.components.MovieAlertDialog
import com.example.movieapp.core.components.MovieAsyncImage
import com.example.movieapp.core.components.MovieText
import com.example.movieapp.core.components.MovieTopBar
import com.example.movieapp.core.util.AppConstants.BASE_IMAGE
import com.example.movieapp.core.util.AppDimensions.DimensionX10
import com.example.movieapp.core.util.AppDimensions.DimensionX12
import com.example.movieapp.core.util.AppDimensions.DimensionX16
import com.example.movieapp.core.util.AppDimensions.DimensionX4
import com.example.movieapp.core.util.AppDimensions.DimensionX8
import com.example.movieapp.core.util.LottieAnimation
import com.example.movieapp.core.util.ResponseResult
import com.example.movieapp.domain.model.Movie

@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
) {

    LaunchedEffect(key1 = Unit) {

        viewModel.getMovies()
    }

    val movieState: ResponseResult<List<Movie>> by viewModel.moviesState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MovieTopBar(
                title = stringResource(id = R.string.app_name),
                actions = {
                    Icon(
                        modifier = Modifier.padding(end = DimensionX12),
                        painter = painterResource(R.drawable.ic_exit_to_app),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = ""
                    )
                }
            )
        },
    ) { paddingValues: PaddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (val currentSate: ResponseResult<List<Movie>> = movieState) {

                is ResponseResult.Idle -> {}

                is ResponseResult.Loading -> {

                    Loading()
                }

                is ResponseResult.Success -> {

                    if (currentSate.value.isNotEmpty()) {

                        MoviesContent(movies = currentSate.value, onMovieClick)
                    } else {

                        LottieAnimation(R.raw.data_not_found)
                    }
                }

                is ResponseResult.Error -> {

                    LottieAnimation(R.raw.error)
                }
            }
        }
    }
}

@Composable
fun MoviesContent(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {

    var showDialog by rememberSaveable { mutableStateOf(true) }

    if (showDialog) {
        MovieAlertDialog(
            title = stringResource(id = R.string.movies_screen_dialog_title_txt),
            text = stringResource(id = R.string.movies_screen_dialog_description_txt),
            onConfirm = { showDialog = false },
            onDismiss = { showDialog = false }
        )
    }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(all = DimensionX12),
        horizontalArrangement = Arrangement.spacedBy(DimensionX16),
        verticalArrangement = Arrangement.spacedBy(DimensionX16)
    ) {

        items(items = movies) { movie: Movie ->

            MovieItem(movie = movie, onMovieClick)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit) {

    Column(
        modifier = Modifier
            .width(173.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Black)
            .clickable { onMovieClick(movie) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {

            MovieAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
                model = if (movie.poster_path.isNotEmpty()) {
                    BASE_IMAGE + movie.poster_path
                } else {
                    R.drawable.image_not_found_
                }
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
                    .padding(horizontal = DimensionX8, vertical = DimensionX10)
            ) {

                MovieText(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(DimensionX4))

                MovieText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "⭐ ${"%.1f".format(movie.vote_average)}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

