package com.example.desafiosicredi.data.api

sealed class Response<out T : Any> {
    data class Success<out T : Any>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}