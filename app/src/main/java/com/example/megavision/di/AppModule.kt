package com.example.megavision.di

import com.example.megavision.data.source.AppRepositoryImpl
import com.example.megavision.data.source.remote.RemoteDataSource
import com.example.megavision.data.source.remote.api.ApiService
import com.example.megavision.data.source.remote.network.InitRetrofit
import com.example.megavision.domain.repository.AppRepository
import com.example.megavision.utils.SessionManager
import com.example.megavision.viewmodel.HomeViewModel
import com.example.megavision.viewmodel.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        InitRetrofit().getInstance(androidContext())
            .createService(ApiService::class.java)
    }
    single {
        SessionManager(androidContext())
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}

val repositoryModule = module {
    single {
        RemoteDataSource(
            get(),
            get(),
        )
    }
    single<AppRepository> {
        AppRepositoryImpl(
            get(),
        )
    }
}
