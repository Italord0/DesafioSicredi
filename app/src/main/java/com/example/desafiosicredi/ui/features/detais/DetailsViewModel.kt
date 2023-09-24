package com.example.desafiosicredi.ui.features.detais

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.EventRepository
import com.example.desafiosicredi.data.repository.MockEventRepository
import com.example.desafiosicredi.nav.MockRouteNavigator
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val eventRepository: EventRepository
) : ViewModel(), RouteNavigator by routeNavigator {

    val event: MutableState<Event?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)

    fun fetchEvent(eventId: String) {
        viewModelScope.launch {
            loading.value = true
            when (val response = eventRepository.getEventById(eventId)) {
                is Response.Success -> {
                    event.value = response.data
                }

                is Response.Error -> {
                    println("Error fetching events: ${response.exception.message}")
                    loading.value = false
                }
            }
        }
    }

    fun onBackClicked() {
        routeNavigator.navigateUp()
    }
}