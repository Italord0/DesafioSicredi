package com.example.desafiosicredi.di

import com.example.desafiosicredi.data.repository.checkin.CheckInRepository
import com.example.desafiosicredi.data.repository.checkin.CheckInRepositoryImpl
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.data.repository.event.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository

    @Binds
    abstract fun bindCheckInRepository(
        checkInRepositoryImpl: CheckInRepositoryImpl
    ): CheckInRepository
}