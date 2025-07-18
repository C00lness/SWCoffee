package com.example.domain.useCases

import com.example.domain.repository.Repository

class ProductsUseCase(private val repository: Repository) {
    fun getProducts(url:String, token: String) = repository.getProducts(url, token)
}