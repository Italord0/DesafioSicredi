package com.example.desafiosicredi.di

import com.example.desafiosicredi.data.api.CheckInApiService
import com.example.desafiosicredi.data.api.EventsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesEventApiService(@Named(RetrofitModule.RETROFIT_BASE_NAMED) retrofit: Retrofit): EventsApiService {
        return retrofit.create(EventsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCheckInApiService(@Named(RetrofitModule.RETROFIT_BASE_NAMED) retrofit: Retrofit): CheckInApiService {
        return retrofit.create(CheckInApiService::class.java)
    }
}