package com.example.desafiosicredi.viewModel

import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import com.example.desafiosicredi.ui.features.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
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
class HomeViewModelTest {

    private val eventRepository: EventRepository = mockk()
    private val routeNavigator: RouteNavigator = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var homeViewModel: HomeViewModel

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
        homeViewModel = HomeViewModel(routeNavigator, eventRepository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch event should call eventRepository getEvents`() {
        coEvery { eventRepository.getEvents() } returns Response.Success(listOf(eventTest))

        runTest {
            homeViewModel.fetchEvents()
        }
        coVerify { eventRepository.getEvents() }
    }

    @Test
    fun `fetch events should return eventList`() {
        coEvery { eventRepository.getEvents() } returns Response.Success(listOf(eventTest))

        runTest {
            homeViewModel.fetchEvents()

            assert(!homeViewModel.loading.value)
            assert(homeViewModel.events.value == listOf(eventTest))
        }
    }

    @Test
    fun `onEventClicked navigates to details`() {
        coEvery { eventRepository.getEvents() } returns Response.Success(listOf(eventTest))

        runTest {
            val event = eventTest
            every { routeNavigator.navigateToRoute(any()) } just runs

            homeViewModel.onEventClicked(event)

            verify { routeNavigator.navigateToRoute("details/${event.id}") }
        }
    }
}
