package com.example.desafiosicredi.data.repository

interface EventRepository {
    suspend fun getEvents()
    suspend fun getEventById(id: String)
}