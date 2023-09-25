package com.example.desafiosicredi.repository

import com.example.desafiosicredi.data.api.EventsApiService
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.data.repository.event.EventRepositoryImpl
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
class EventRepositoryTest {

    private val eventService: EventsApiService = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var eventRepository: EventRepository

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
        eventRepository = EventRepositoryImpl(eventService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getEvents should call eventService getEvents`() {
        coEvery { eventService.getEvents() } returns listOf(eventTest)

        runTest {
            eventRepository.getEvents()
        }

        coVerify { eventService.getEvents() }
    }

    @Test
    fun `getEventById should call eventService getEventById`() {
        coEvery { eventService.getEventById(any()) } returns eventTest

        runTest {
            eventRepository.getEventById("")
        }

        coVerify { eventService.getEventById(any()) }
    }

}