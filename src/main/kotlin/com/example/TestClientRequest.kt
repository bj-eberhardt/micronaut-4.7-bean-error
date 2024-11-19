package com.example

import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.ClientFilter
import io.micronaut.http.annotation.RequestFilter
import jakarta.inject.Singleton

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class TestClientRequest


@TestClientRequest
@ClientFilter
@Singleton
class TestClientFilter  {
    @RequestFilter
    fun doFilter(request: MutableHttpRequest<*>) {
        request.basicAuth("user", "password")
    }
}
