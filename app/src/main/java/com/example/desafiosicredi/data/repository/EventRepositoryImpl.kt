package com.example.desafiosicredi.data.repository

import com.example.desafiosicredi.data.api.EventsApiService
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventsApiService: EventsApiService
) : EventRepository {

    override suspend fun getEvents(): Response<List<Event>> {
        return try {
            val events = eventsApiService.getEvents()
            Response.Success(events)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun getEventById(id: String): Response<Event> {
        return try {
            val event = eventsApiService.getEventById(id)
            Response.Success(event)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}