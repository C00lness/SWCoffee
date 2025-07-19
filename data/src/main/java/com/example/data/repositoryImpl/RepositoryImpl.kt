package com.example.data.repositoryImpl

import android.app.Activity
import android.util.Log
import com.example.data.api.RetrofitService
import com.example.data.getCurrentLocationFun
import com.example.domain.entities.Point
import com.example.domain.entities.RequestUser
import com.example.domain.repository.Repository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RepositoryImpl(private val retrofitService: RetrofitService): Repository {
    override suspend fun registration(user: RequestUser): String {
        var jsonObject = JSONObject()
        jsonObject.put("login", user.login)
        jsonObject.put("password", user.password)
        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val response = retrofitService.register(requestBody)
        if (response.code() == 200)
        {
            val body = response.body().toString()
            Log.d("tenpLog", body.toString())
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(response.body()?.string())
            return prettyJson.substring(14).substringBefore("\\")
            // Изящность этой функции вероятно вызывает некоторые сомнения, но к сожалению лучше я
            // пока с requestBody и POST не придумал)
        }
        else
            return response.code().toString()
    }

    override suspend fun login(user: RequestUser): String {
        var jsonObject = JSONObject()
        jsonObject.put("login", user.login)
        jsonObject.put("password", user.password)
        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val response = retrofitService.login(requestBody)
        if (response.code() == 200)
        {
            val body = response.body().toString()
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(response.body()?.string())
            return prettyJson.substring(14).substringBefore("\\")
            // Настолько же, понимаю, ужасно выглядит как та что выше)
        }
        else
            return response.code().toString()
    }

    override fun getLocations(token: String) = flow{
        emit(retrofitService.getLocations(token))
    }

    override fun getProducts(url: String, token: String) = flow{
        emit(retrofitService.getProducts(url, token))
    }

    override fun getCurrentLocation(activity: Activity) = getCurrentLocationFun(activity)
}