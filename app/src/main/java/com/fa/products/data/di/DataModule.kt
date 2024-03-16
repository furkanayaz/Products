package com.fa.products.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.Charsets
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DataModule {

    @[Singleton Provides]
    fun provideKtorClient(): HttpClient {
        return HttpClient(engineFactory = CIO) {
            Charsets {
                // It already uses this by default.
                register(Charsets.UTF_8)
            }
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10_000
                connectTimeoutMillis = 10_000
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
                /*filter { request ->
                    request.url.host.contains("ktor.io")
                }*/
            }
        }
    }

}