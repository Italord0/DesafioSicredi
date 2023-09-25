package com.example.desafiosicredi.data.repository.checkin

import com.example.desafiosicredi.data.api.Response

class MockCheckInRepository : CheckInRepository {
    override suspend fun doCheckIn(eventId: String, name: String, email: String): Response<Unit> {
        return Response.Success(data = Unit)
    }
}