package com.example.users.customers

import norm.query
import normfile.AddUserParams
import normfile.AddUserQuery
import normfile.LoginUserParams
import normfile.LoginUserQuery
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class CustomerRepository(@Inject private val dataSource: DataSource) {
    fun addNewUser(name: String, age: Int, email: String, password: String): ReturnUserDetails? =

        dataSource.connection.use { connection ->
            AddUserQuery().query(
                connection,
                AddUserParams(
                    name,
                    age,
                    email,
                    password
                )
            )
        }.map {
            ReturnUserDetails(it.id, it.name!!, it.age!!, it.email!!)
        }.firstOrNull()

    fun loginUser(email: String): Customer? =
        dataSource.connection.use { connection ->
            LoginUserQuery().query(
                connection,
                LoginUserParams(email)
            )
        }.map { Customer(it.name!!, it.age!!, it.email!!, it.password!!) }.firstOrNull()


}