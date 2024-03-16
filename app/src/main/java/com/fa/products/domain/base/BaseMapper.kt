package com.fa.products.domain.base

/*
* [R] is a DTO class.
* [T] is a Ui Model class.
* */

interface BaseMapper<R: Any, T: Any> {
    fun R.mapToModel(): T
    fun T.mapToDto(): R
}