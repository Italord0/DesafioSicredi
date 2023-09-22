package com.example.desafiosicredi.data.api

import com.example.desafiosicredi.data.model.Event
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApiService {
    @GET("events")
    suspend fun getEvents(): List<Event>

    @GET("events/{id}")
    suspend fun getEventById(
        @Path("id") id: String
    ): Event
}