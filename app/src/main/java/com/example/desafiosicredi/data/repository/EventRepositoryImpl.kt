package com.example.desafiosicredi.data.repository

import com.example.desafiosicredi.data.api.EventsApiService
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventsApiService: EventsApiService
) : EventRepository {
    override suspend fun getEvents() {
        eventsApiService.getEvents()
    }

    override suspend fun getEventById(id: String) {
        eventsApiService.getEventById(id)
    }
}