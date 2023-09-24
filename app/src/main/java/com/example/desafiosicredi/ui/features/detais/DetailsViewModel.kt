package com.example.desafiosicredi.ui.features.detais

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiosicredi.data.api.Response
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.data.repository.checkin.CheckInRepository
import com.example.desafiosicredi.data.repository.event.EventRepository
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val eventRepository: EventRepository,
    private val checkInRepository: CheckInRepository
) : ViewModel(), RouteNavigator by routeNavigator {

    val event: MutableState<Event?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val showCheckInDialog: MutableState<Boolean> = mutableStateOf(false)
    val checkInResult: MutableState<String> = mutableStateOf("")

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

    fun doCheckIn(eventId: String) {
        viewModelScope.launch {
            showCheckInDialog.value = false
            try {
                val response = checkInRepository.doCheckIn(
                    eventId = eventId,
                    name = "Test1",
                    email = "test1@test.com"
                )

                when (response) {
                    is Response.Success -> {
                        showCheckInDialog.value = true
                        checkInResult.value = "Check-in realizado com sucesso"
                    }

                    is Response.Error -> {
                        showCheckInDialog.value = true
                        checkInResult.value = "Erro check-in : ${response.exception.message}"
                    }
                }
            } catch (e: Exception) {
                showCheckInDialog.value = true
                checkInResult.value = "Erro check-in : ${e.message}"
            }
        }
    }

    fun hideCheckInDialog() {
        showCheckInDialog.value = false
    }

    fun onBackClicked() {
        routeNavigator.navigateUp()
    }
}