package com.example.users.customers

import com.example.users.Exceptions.AuthException
import org.mindrot.jbcrypt.BCrypt
import java.net.http.HttpResponse
import java.sql.SQLException
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CustomerService(@Inject  private val customerRepository: CustomerRepository){

    fun addNewUser(name: String, age: Int, email: String, password: String):ReturnUserDetails? {
        if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches())throw AuthException("Wrong Email format")
        val hashedPassword: String = BCrypt.hashpw(password, BCrypt.gensalt(10))
         try {
             return customerRepository.addNewUser(name, age, email, hashedPassword)
         } catch (e:SQLException)
         {
             throw AuthException("EmailId already exists")
         }

    }
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun loginUser(email: String,password: String)
    {

        val user=customerRepository.loginUser(email)
        if(user!=null) {
            if (BCrypt.checkpw(password, user!!.password)) {
                print("GINNIIIIIII")
            } else throw AuthException("Incorrect password")
        }
        else
        {
            throw AuthException("Invalid emailid")
        }
    }
}