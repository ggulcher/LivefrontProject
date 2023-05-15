package com.example.livefrontproject.network

import kotlinx.serialization.json.Json

object NetworkingSingleton {

    val AppJson: Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }
}