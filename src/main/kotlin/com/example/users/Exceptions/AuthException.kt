package com.example.users.Exceptions

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.lang.RuntimeException

class AuthException(message:String): HttpStatusException(HttpStatus.UNAUTHORIZED,message)
