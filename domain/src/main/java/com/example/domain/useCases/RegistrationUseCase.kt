package com.example.domain.useCases

import com.example.domain.entities.RequestUser
import com.example.domain.repository.Repository

class RegistrationUseCase(private val repository: Repository) {
    suspend fun registration(user: RequestUser) = repository.registration(user)
}