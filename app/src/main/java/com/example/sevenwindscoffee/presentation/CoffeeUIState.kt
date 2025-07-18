package com.example.sevenwindscoffee.presentation

import com.example.domain.entities.Location
import com.example.domain.entities.Product

sealed interface CoffeeUIState {
    data class SuccessLocations(val locations: List<Location>) : CoffeeUIState
    data class SuccessProducts(val locations: List<Product>) : CoffeeUIState
    data class Error(val message: String) : CoffeeUIState
    data object Loading : CoffeeUIState
}