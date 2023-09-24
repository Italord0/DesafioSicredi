package com.example.desafiosicredi.data.repository.event

import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event

interface EventRepository {
    suspend fun getEvents(): Response<List<Event>>
    suspend fun getEventById(id: String): Response<Event>
}