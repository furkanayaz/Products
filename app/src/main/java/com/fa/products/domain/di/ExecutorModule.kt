package com.fa.products.domain.di

import com.fa.products.domain.services.Executor
import com.fa.products.domain.services.ExecutorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@[Module InstallIn(ViewModelComponent::class)]
interface ExecutorModule {

    @get:[Binds ViewModelScoped]
    val ExecutorImpl.executor: Executor

}