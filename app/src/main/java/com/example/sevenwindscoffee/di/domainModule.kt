package com.example.sevenwindscoffee.di

import com.example.domain.useCases.LocationsUseCase
import com.example.domain.useCases.LoginUseCase
import com.example.domain.useCases.ProductsUseCase
import com.example.domain.useCases.RegistrationUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<RegistrationUseCase> {
        RegistrationUseCase(repository = get())
    }

    factory<LoginUseCase> {
        LoginUseCase(repository = get())
    }

    factory<LocationsUseCase> {
        LocationsUseCase(repository = get())
    }

    factory<ProductsUseCase> {
        ProductsUseCase(repository = get())
    }

}