package com.ciscodeto.carregistro.infrastructure.api

import android.content.res.Resources
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest

fun provideHttpClient(): HttpClient {
    val client = HttpClient() {
        expectSuccess = true
        defaultRequest {
            url("https://processo-seletivo-653592723157.southamerica-east1.run.app/")
        }
    }

    return client
}