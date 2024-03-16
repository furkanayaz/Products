package com.fa.products.domain

sealed class Result<out T> {
    class Success<R>(val data: R) : Result<R>()
    class Error(val message: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}