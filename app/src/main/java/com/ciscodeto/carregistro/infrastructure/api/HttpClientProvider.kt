package com.ciscodeto.carregistro.infrastructure.api

import com.ciscodeto.carregistro.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.defaultRequest

fun provideHttpClient(): HttpClient {
    val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url(BuildConfig.BASE_URL)
        }
    }

    return client
}