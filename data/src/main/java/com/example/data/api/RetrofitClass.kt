package com.example.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://212.41.30.90:35005/"
fun getRetrofitInstance(base_Url: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base_Url)
        .build()
}

fun getRetrofitInterface(retrofit: Retrofit): RetrofitService {
    return retrofit.create(RetrofitService::class.java)
}