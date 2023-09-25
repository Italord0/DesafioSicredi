package com.example.desafiosicredi.repository

import com.example.desafiosicredi.data.api.CheckInApiService
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.checkin.CheckInRepository
import com.example.desafiosicredi.data.repository.checkin.CheckInRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheckInRepositoryTest {

    private val checkInService: CheckInApiService = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var checkInRepository: CheckInRepository

    private val eventTest = Event(
        id = "1",
        title = "Event 1",
        description = "Event description 1",
        date = System.currentTimeMillis(),
        people = listOf(),
        image = "Image Test 1",
        longitude = 0.0,
        latitude = 0.0,
        price = 0.0
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        checkInRepository = CheckInRepositoryImpl(checkInService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `doCheckIn should call checkInService performCheckIn`() {
        coEvery { checkInService.performCheckIn(mockk()) } returns mockk()

        runTest {
            checkInRepository.doCheckIn("", "", "")
        }

        coVerify { checkInService.performCheckIn(any()) }
    }

}