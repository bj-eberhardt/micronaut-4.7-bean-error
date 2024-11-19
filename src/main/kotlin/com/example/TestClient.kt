package com.example

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://www.google.de")
@TestClientRequest
interface TestClient {
    @Get()
    fun read(): String
}