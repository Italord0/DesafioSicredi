package com.example.desafiosicredi.data.repository

import com.example.desafiosicredi.data.source.EventsDataSource
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventsDataSource: EventsDataSource
) : EventRepository {
    override suspend fun getEvents() {
        eventsDataSource.getEvents()
    }

    override suspend fun getEventById(id: String) {
        eventsDataSource.getEventById(id)
    }
}