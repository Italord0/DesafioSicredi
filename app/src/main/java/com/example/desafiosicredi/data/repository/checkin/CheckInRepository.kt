package com.example.desafiosicredi.data.repository.checkin

import com.example.desafiosicredi.data.api.Response

interface CheckInRepository {
    suspend fun doCheckIn(
        eventId: String,
        name: String,
        email: String,
    ): Response<Unit>
}