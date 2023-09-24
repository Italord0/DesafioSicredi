package com.example.desafiosicredi.data.repository.checkin

import com.example.desafiosicredi.data.api.CheckInApiService
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.request.CheckInRequest
import retrofit2.awaitResponse
import javax.inject.Inject

class CheckInRepositoryImpl @Inject constructor(
    private val checkInApiService: CheckInApiService
) : CheckInRepository {
    override suspend fun doCheckIn(eventId: String, name: String, email: String): Response<Unit> {
        return try {
            val response = checkInApiService.performCheckIn(CheckInRequest(eventId, name, email))
                .awaitResponse()
            when (response.isSuccessful) {
                true -> return Response.Success(Unit)
                false -> return Response.Error(
                    exception = CheckInRepositoryException(
                        message = response.errorBody()?.string() ?: "Error code: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}

class CheckInRepositoryException(message: String) : Exception(message)
