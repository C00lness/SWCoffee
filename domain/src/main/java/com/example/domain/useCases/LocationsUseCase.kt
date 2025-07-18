package com.example.domain.useCases

import com.example.domain.repository.Repository

class LocationsUseCase(private val repository: Repository) {
    fun getLocations(token: String) = repository.getLocations(token)
}