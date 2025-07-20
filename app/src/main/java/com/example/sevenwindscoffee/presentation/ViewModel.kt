package com.example.sevenwindscoffee.presentation

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Point
import com.example.domain.entities.RequestUser
import com.example.domain.useCases.CurrentLocationUseCase
import com.example.domain.useCases.LocationsUseCase
import com.example.domain.useCases.LoginUseCase
import com.example.domain.useCases.ProductsUseCase
import com.example.domain.useCases.RegistrationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ViewModel(private val registrationUseCase: RegistrationUseCase,
                private val loginUseCase: LoginUseCase,
                private val locationsUseCase: LocationsUseCase,
                private val productsUseCase: ProductsUseCase,
                private val currentLocationUseCase: CurrentLocationUseCase): ViewModel() {

    private val _tokenState = mutableStateOf("")
    val tokenState: State<String?> = _tokenState

    private val _locationsState = MutableStateFlow<CoffeeUIState>(CoffeeUIState.Loading)
    val locationsState: StateFlow<CoffeeUIState> = _locationsState

    private val _productsState = MutableStateFlow<CoffeeUIState>(CoffeeUIState.Loading)
    val productsState: StateFlow<CoffeeUIState> = _productsState

    private val _currentLocationState = MutableStateFlow<String>("")
    val currentLocationState: StateFlow<String> = _currentLocationState

    fun getLocations(token: String) = viewModelScope.launch {
        _locationsState.value = CoffeeUIState.Loading
        locationsUseCase.getLocations(token)
            .flowOn(Dispatchers.IO)
            .catch { _locationsState.emit(CoffeeUIState.Error(it.message.toString()))}
            .collect{value -> _locationsState.emit(CoffeeUIState.SuccessLocations(value))}
    }

    fun getProducts(url: String, token: String) = viewModelScope.launch {
        _productsState.value = CoffeeUIState.Loading
        productsUseCase.getProducts(url, token)
            .flowOn(Dispatchers.IO)
            .catch { _productsState.emit(CoffeeUIState.Error(it.message.toString()))}
            .collect{value -> _productsState.emit(CoffeeUIState.SuccessProducts(value))}
    }

    fun regLoginLocations(user: RequestUser) = viewModelScope.launch {
        var res = registrationUseCase.registration(user)
        if (res != "400")
        {
            res = loginUseCase.login(user)
            if ((res != "400") && (res != "404"))
            {
                _tokenState.value = "Bearer " + res
                getLocations(_tokenState.value)
            }
        }
    }

    fun loginLocations(user: RequestUser) = viewModelScope.launch {
        val res = loginUseCase.login(user)
        if ((res != "400") && (res != "404"))
        {
            _tokenState.value = "Bearer " + res
            getLocations(_tokenState.value)
        }
    }

    fun getCurrentLocation(activity: Activity) =
        viewModelScope.launch {
            currentLocationUseCase.getCurrentLocations(activity)
                .flowOn(Dispatchers.IO)
                .catch { Log.d("tempLog", it.message.toString()) }
                .collect{value->_currentLocationState.emit(value)}
        }
}