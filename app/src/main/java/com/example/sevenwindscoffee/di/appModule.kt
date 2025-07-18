package com.example.sevenwindscoffee.di

import com.example.sevenwindscoffee.presentation.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ViewModel(registrationUseCase = get(), loginUseCase = get(),
        locationsUseCase = get(), productsUseCase = get()) }
}