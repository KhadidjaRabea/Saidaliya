package com.example.myapplication.Server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var adresse = "192.168.1.3:8082"
    val endpoint :Endpoint by lazy {
        // TODO : Put base URL
        Retrofit.Builder().baseUrl("http://192.168.1.3:8082").
            addConverterFactory(GsonConverterFactory.create()).
            build().create(Endpoint::class.java)
    }
}
