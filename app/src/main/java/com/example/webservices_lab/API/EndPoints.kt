package com.example.webservices_lab.API

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @GET("/users/")
    fun getUsers(): Call<List<WebServiceUserOutput>>
    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<WebServiceUserOutput>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") first: String?): Call<OutputPost>

}

