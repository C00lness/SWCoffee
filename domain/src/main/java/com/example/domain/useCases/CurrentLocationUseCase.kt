package com.example.domain.useCases

import android.app.Activity
import com.example.domain.repository.Repository

class CurrentLocationUseCase(private val repository: Repository) {
    fun getCurrentLocations(activity: Activity) = repository.getCurrentLocation(activity)
}
