package com.example.sevenwindscoffee.di


import com.example.data.api.BASE_URL
import com.example.data.api.getRetrofitInstance
import com.example.data.api.getRetrofitInterface
import com.example.data.getCurrentLocationFun
import com.example.data.repositoryImpl.RepositoryImpl
import com.example.domain.repository.Repository
import com.example.sevenwindscoffee.presentation.MainActivity
import org.koin.dsl.module

val dataModule = module {
    single { BASE_URL }
    single { getRetrofitInstance(get()) }
    single { getRetrofitInterface(get()) }

    // Не знаю правильно ли это с точки зрения архитектуры когда presentation зависит от data
    scope<MainActivity> {
        scoped { getCurrentLocationFun(get())}
    }

    single<Repository> {
        RepositoryImpl(retrofitService = get())
    }
}