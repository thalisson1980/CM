package com.example.webservices_lab.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()
<<<<<<< Updated upstream:app/src/main/java/com/example/webservices_lab/API/ServiceBuilder.kt
    private val retrofit = Retrofit.Builder() .baseUrl("https://restcountries.com/v3.1/name/portugal") .addConverterFactory(GsonConverterFactory.create()) .client(client)
=======
    private val retrofit = Retrofit.Builder() .baseUrl("https://restcountries.com/v3.1/name/Portugal") .addConverterFactory(GsonConverterFactory.create()) .client(client)
>>>>>>> Stashed changes:app/src/main/java/com/example/lab_maps/API/serviceBuilder.kt
        .build()
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service) }

}

