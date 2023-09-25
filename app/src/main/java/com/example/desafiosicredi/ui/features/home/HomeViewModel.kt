package com.example.desafiosicredi.ui.features.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val eventRepository: EventRepository
) : ViewModel(), RouteNavigator by routeNavigator {

    val events: MutableState<List<Event>> = mutableStateOf(listOf())
    val loading: MutableState<Boolean> = mutableStateOf(false)

    init {
        fetchEvents()
    }

    fun fetchEvents() {
        viewModelScope.launch {
            loading.value = true
            when (val response = eventRepository.getEvents()) {
                is Response.Success -> {
                    events.value = response.data
                    loading.value = false
                }

                is Response.Error -> {
                    println("Error fetching events: ${response.exception.message}")
                    loading.value = false
                }
            }
        }
    }

    fun onEventClicked(event: Event) {
        routeNavigator.navigateToRoute("details/${event.id}")
    }

}