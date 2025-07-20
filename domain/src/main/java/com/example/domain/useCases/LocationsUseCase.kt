package com.example.domain.useCases

import android.content.Context
import com.example.domain.repository.Repository

class LocationsUseCase(private val repository: Repository) {
    fun getLocations(token: String, context: Context) = repository.getLocations(token, context)
}