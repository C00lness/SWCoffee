package com.example.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://212.41.30.90:35005/"

fun getClient(auth: String): OkHttpClient
{
    val c = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + auth)
            .build()
        chain.proceed(newRequest)
    }.build()
    return c
}
var client = OkHttpClient.Builder().addInterceptor { chain ->
    val newRequest: Request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer " + "")
        .build()
    chain.proceed(newRequest)
}.build()

fun getRetrofitInstance(base_Url: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base_Url)
        .build()
}

fun getRetrofitInterface(retrofit: Retrofit): RetrofitService {
    return retrofit.create(RetrofitService::class.java)
}