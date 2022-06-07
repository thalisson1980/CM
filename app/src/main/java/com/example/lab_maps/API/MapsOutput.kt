package com.example.lab_maps.API

data class MapsOutput (
        val id: Int,
        val name: String,
        val email: String,
        val address: Address
        )

data class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: Geo
    )

data class Geo(
        val lat: String,
        val lng: String
    )

