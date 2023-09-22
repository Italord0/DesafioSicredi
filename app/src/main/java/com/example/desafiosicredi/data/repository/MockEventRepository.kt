package com.example.desafiosicredi.data.repository

import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event

class MockEventRepository : EventRepository {
    override suspend fun getEvents(): Response<List<Event>> {
        return Response.Success(data = eventsListMock)
    }

    override suspend fun getEventById(id: String): Response<Event> {
        return Response.Success(
            data = eventMock
        )
    }

    companion object {
        val eventMock = Event(
            people = listOf("Joãozinho", "Maria Joaquina"),
            date = System.currentTimeMillis(),
            description = "Descricao do evento",
            image = "https://source.unsplash.com/random/200x200?sig=1",
            longitude = 234.567,
            latitude = 89.012,
            price = 49.99,
            title = "Evento",
            id = "1"
        )
        val eventsListMock = (1..10).map { eventMock.copy(id = it.toString()) }
    }
}