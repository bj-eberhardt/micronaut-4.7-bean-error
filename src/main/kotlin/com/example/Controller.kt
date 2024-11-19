package com.example
import io.micronaut.http.annotation.Controller
import io.micronaut.context.annotation.Context
import io.micronaut.http.annotation.Get

@Controller("/test")
@Context
class Controller(val client: TestClient) {

    @Get
    fun read(): String = client.read()

}