package com.example.desafiosicredi.ui.features.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val repository: EventRepository
) : ViewModel(), RouteNavigator by routeNavigator {

    val events: MutableState<List<Event>> = mutableStateOf(listOf())

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            when (val response = repository.getEvents()) {
                is Response.Success -> {
                    events.value = response.data
                }

                is Response.Error -> {
                    // Handle the error, e.g., show a toast or log the error
                    println("Error fetching events: ${response.exception.message}")
                }
            }
        }
    }

    fun onEventClicked(event: Event) {
        // todo : navigate to event screen
        println(event)
    }

}