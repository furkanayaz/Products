package com.fa.products.domain.services

import kotlinx.coroutines.CoroutineDispatcher

interface Executor {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}