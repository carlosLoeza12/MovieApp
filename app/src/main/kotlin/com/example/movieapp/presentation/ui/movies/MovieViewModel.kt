package com.example.movieapp.presentation.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.util.ResponseResult
import com.example.movieapp.di.IoDispatcher
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    @param:IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _moviesState: MutableStateFlow<ResponseResult<List<Movie>>> = MutableStateFlow(ResponseResult.Idle)
    val moviesState: StateFlow<ResponseResult<List<Movie>>> = _moviesState.asStateFlow()

    private var isDataLoading = false

    fun getMovies() {

        if(isDataLoading.not()) {

            viewModelScope.launch {

                _moviesState.value = ResponseResult.Loading

                try {

                    val result: List<Movie> = withContext(dispatcher) {

                        getMoviesUseCase()
                    }

                    isDataLoading = true
                    _moviesState.value = ResponseResult.Success(result)
                } catch (e: Exception) {

                    _moviesState.value = ResponseResult.Error(e.localizedMessage ?: "An unexpected error occurred")
                }
            }
        }
    }
}

