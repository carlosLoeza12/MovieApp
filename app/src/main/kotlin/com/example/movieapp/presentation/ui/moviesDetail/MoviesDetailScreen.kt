package com.example.movieapp.presentation.ui.moviesDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.R
import com.example.movieapp.core.components.Loading
import com.example.movieapp.core.components.MovieAsyncImage
import com.example.movieapp.core.components.MovieText
import com.example.movieapp.core.components.MovieTopBar
import com.example.movieapp.core.util.AppConstants.BASE_IMAGE
import com.example.movieapp.core.util.AppDimensions.DimensionX12
import com.example.movieapp.core.util.AppDimensions.DimensionX16
import com.example.movieapp.core.util.AppDimensions.DimensionX8
import com.example.movieapp.core.util.LottieAnimation
import com.example.movieapp.core.util.ResponseResult
import com.example.movieapp.domain.model.ExtrasMovieDetail
import com.example.movieapp.domain.model.MovieDetail

@Composable
fun MoviesDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    movieDetail: MovieDetail
) {

    val movieState: ResponseResult<ExtrasMovieDetail> by viewModel.moviesDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = movieDetail.id) {

        viewModel.getExtrasMovieDetail(movieDetail.id)
    }

    Scaffold(
        topBar = {
            MovieTopBar(
                title = stringResource(id = R.string.app_name),
                navigation = {
                    Icon(
                        modifier = Modifier
                            .padding(start = DimensionX12)
                            .clickable { navigateBack() },
                        painter = painterResource(R.drawable.ic_back),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = ""
                    )
                }
            )
        }
    ) { paddingValues: PaddingValues ->

        when (val currentSate: ResponseResult<ExtrasMovieDetail> = movieState) {

            is ResponseResult.Idle -> {}

            is ResponseResult.Loading -> {

                Loading()
            }

            is ResponseResult.Success -> {

                MovieDetailContent(
                    movieDetail = movieDetail,
                    extrasMovieDetail = currentSate.value,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is ResponseResult.Error -> {

                LottieAnimation(R.raw.error)
            }
        }
    }

}

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail,
    extrasMovieDetail: ExtrasMovieDetail,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {

        MovieAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = BASE_IMAGE + movieDetail.poster_path,
            contentScale = ContentScale.FillBounds
        )

        Spacer(Modifier.height(DimensionX16))

        Column(modifier = Modifier.padding(horizontal = DimensionX16)) {

            MovieText(
                text = movieDetail.original_title,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(DimensionX8))

            MovieText(
                text = movieDetail.overview,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 8
            )

            Spacer(Modifier.height(DimensionX16))

            MovieText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.movie_details_release_date),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleSmall
            )

            MovieText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = DimensionX8),
                text = movieDetail.release_date,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleSmall
            )

            MovieText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.movie_details_genres),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleSmall
            )

            MovieText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = DimensionX8),
                text = extrasMovieDetail.genres.joinToString(", ") { it.name },
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleSmall
            )

            MovieText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.movie_details_runtime),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleSmall
            )

            MovieText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = DimensionX8),
                text = stringResource(R.string.movie_details_runtime_format, extrasMovieDetail.runtime),
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

