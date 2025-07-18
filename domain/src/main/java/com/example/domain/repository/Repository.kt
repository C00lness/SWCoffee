package com.example.domain.repository

import com.example.domain.entities.Location
import com.example.domain.entities.Product
import com.example.domain.entities.RequestUser
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun registration(user: RequestUser): String
    suspend fun login(user: RequestUser): String
    fun getLocations(token: String): Flow<List<Location>>
    fun getProducts(url: String, token: String): Flow<List<Product>>
}