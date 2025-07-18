package com.example.sevenwindscoffee.presentation
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.useCases.LocationsUseCase
import com.example.domain.useCases.LoginUseCase
import com.example.domain.useCases.ProductsUseCase
import com.example.domain.useCases.RegistrationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Location
import com.example.domain.entities.Product
import com.example.domain.entities.RequestUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ViewModel(private val registrationUseCase: RegistrationUseCase,
                private val loginUseCase: LoginUseCase,
                private val locationsUseCase: LocationsUseCase,
                private val productsUseCase: ProductsUseCase): ViewModel() {

    private val _registrationState = mutableStateOf("")
    val registrationState: State<String?> = _registrationState

    private val _loginState = mutableStateOf("")
    val loginState: State<String?> = _loginState

    private val _locationsState = MutableStateFlow<List<Location>>(listOf())
    val locationsState: StateFlow<List<Location>> = _locationsState

    private val _productsState = MutableStateFlow<List<Product>>(listOf())
    val productsState: StateFlow<List<Product>> = _productsState

    fun registration(user: RequestUser) = viewModelScope.launch {
        _registrationState.value = registrationUseCase.registration(user)
    }

    fun login(user: RequestUser) = viewModelScope.launch {
        _loginState.value = loginUseCase.login(user)
    }

    fun getLocations(token: String) = viewModelScope.launch {
        locationsUseCase.getLocations(token)
            .flowOn(Dispatchers.IO)
            .catch { Log.d("tempLog", it.message.toString())}
            .collect{value -> _locationsState.emit(value)}
    }

    fun getProducts(url: String, token: String) = viewModelScope.launch {
        productsUseCase.getProducts(url, token)
            .flowOn(Dispatchers.IO)
            .catch { Log.d("tempLog", it.message.toString())}
            .collect{value -> _productsState.emit(value)}
    }
}