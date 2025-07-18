package com.example.data.api

import com.example.domain.entities.Location
import com.example.domain.entities.Product
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url


interface RetrofitService {
    @POST("auth/login")
    suspend fun login(@Body requestBody: RequestBody): Response<ResponseBody>
    @POST("auth/register")
    suspend fun register(@Body requestBody: RequestBody): Response<ResponseBody>
    @GET("locations")
    suspend fun getLocations(@Header("Authorization") token: String): List<Location>
    @GET()
    suspend fun getProducts(@Url url : String, @Header("Authorization") token: String): List<Product>
}