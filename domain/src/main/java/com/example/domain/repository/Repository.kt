package com.example.domain.repository

import android.app.Activity
import com.example.domain.entities.Location
import com.example.domain.entities.Point
import com.example.domain.entities.Product
import com.example.domain.entities.RequestUser
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun registration(user: RequestUser): String
    suspend fun login(user: RequestUser): String
    fun getLocations(token: String): Flow<List<Location>>
    fun getProducts(url: String, token: String): Flow<List<Product>>
    fun getCurrentLocation(activity: Activity): Flow<String>
}