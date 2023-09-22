package com.example.desafiosicredi.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val repository: EventRepository
) : ViewModel(), RouteNavigator by routeNavigator {

    init {
        fetchEvents()
    }
    private fun fetchEvents() {
        viewModelScope.launch {
            repository.getEvents()
        }
    }

    fun onEventClicked(event: Event) {
        // todo : navigate to event screen
        println(event)
    }

}