package com.example.lab_maps.API

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @GET("/users/")
    fun getUsers(): Call<List<MapsOutput>>

}