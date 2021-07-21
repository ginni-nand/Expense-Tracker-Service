package com.example.users.customers

import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.net.http.HttpResponse
import javax.inject.Inject

@Controller("/api")
class CustomerController (@Inject private  val customerService: CustomerService){
    @Post("/register")
    fun addNewUser(@Body customer: Customer)
    {  customerService.addNewUser(customer.name,customer.age,customer.email,customer.password)
    }
    @Post("/login")
    fun loginUser(@Body loginDetails: loginDetails)
    {
      customerService.loginUser(loginDetails.email,loginDetails.password)
    }

}