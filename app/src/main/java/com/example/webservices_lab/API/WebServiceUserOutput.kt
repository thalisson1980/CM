package com.example.webservices_lab.API

data class WebServiceUserOutput (
    val id: Int,
    val name: String,
    val email: String,
    val address: Address
)

data class Address (

    val city: String,
    val zipcode: String

)

