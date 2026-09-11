package com.example.movieapp.core.util

sealed class ResponseResult<out T> {

    object Loading: ResponseResult<Nothing>()

    object Idle: ResponseResult<Nothing>()

    data class Success<out T>(val value: T): ResponseResult<T>()

    data class Error(val message: String, val cause: Throwable? = null): ResponseResult<Nothing>()
}