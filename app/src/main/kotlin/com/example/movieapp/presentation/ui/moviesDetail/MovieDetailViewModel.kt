package com.example.movieapp.presentation.ui.moviesDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.util.ResponseResult
import com.example.movieapp.di.IoDispatcher
import com.example.movieapp.domain.model.ExtrasMovieDetail
import com.example.movieapp.domain.usecase.GetExtrasMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getExtrasMovieDetailUseCase: GetExtrasMovieDetailUseCase,
    @param:IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _moviesDetailState: MutableStateFlow<ResponseResult<ExtrasMovieDetail>> = MutableStateFlow(ResponseResult.Idle)
    val moviesDetailState: StateFlow<ResponseResult<ExtrasMovieDetail>> = _moviesDetailState.asStateFlow()

    fun getExtrasMovieDetail(movieId: Int) {

        viewModelScope.launch {

            _moviesDetailState.value = ResponseResult.Loading

            try {

                val result: ExtrasMovieDetail = withContext(dispatcher) {

                    getExtrasMovieDetailUseCase(movieId)
                }

                _moviesDetailState.value = ResponseResult.Success(result)
            } catch (e: Exception) {

                _moviesDetailState.value = ResponseResult.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

}