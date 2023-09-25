package com.example.desafiosicredi.viewModel

import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.checkin.CheckInRepository
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import com.example.desafiosicredi.ui.features.detais.DetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
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
class DetailsViewModelTest {

    private val eventRepository: EventRepository = mockk()
    private val checkInRepository: CheckInRepository = mockk()
    private val routeNavigator: RouteNavigator = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var detailsViewModel: DetailsViewModel

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
        detailsViewModel = DetailsViewModel(routeNavigator, eventRepository, checkInRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch event should call eventRepository getEventById`() {
        coEvery { eventRepository.getEventById(any()) } returns Response.Success(eventTest)

        runTest {
            detailsViewModel.fetchEvent("")
        }
        coVerify { eventRepository.getEventById(any()) }
    }

    @Test
    fun `fetch event should return eventTest`() {
        coEvery { eventRepository.getEventById(any()) } returns Response.Success(eventTest)

        runTest {
            detailsViewModel.fetchEvent("")
        }
        assert(detailsViewModel.event.value == eventTest)
    }

    @Test
    fun `doCheckIn should call checkInRepository doCheckIn`() {
        coEvery { checkInRepository.doCheckIn(any(), any(), any()) }

        runTest {
            detailsViewModel.doCheckIn("", "", "")
        }
        coVerify { checkInRepository.doCheckIn(any(), any(), any()) }
    }

}