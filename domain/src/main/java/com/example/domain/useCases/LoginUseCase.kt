package com.example.domain.useCases

import com.example.domain.entities.RequestUser
import com.example.domain.repository.Repository

class LoginUseCase(private val repository: Repository) {
    suspend fun login(user: RequestUser) = repository.login(user)
}
