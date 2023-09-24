package com.example.desafiosicredi.data.api

import com.example.desafiosicredi.data.model.request.CheckInRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInApiService {
    @POST("checkin")
    fun performCheckIn(@Body checkInRequest: CheckInRequest): Call<Void>
}
