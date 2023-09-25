package com.example.desafiosicredi.ui.features.detais

import com.example.desafiosicredi.data.repository.checkin.MockCheckInRepository
import com.example.desafiosicredi.data.repository.event.MockEventRepository
import com.example.desafiosicredi.nav.MockRouteNavigator

class PreviewDetailsViewModel : DetailsViewModel(
    MockRouteNavigator(),
    MockEventRepository(),
    MockCheckInRepository()
) {
    init {
        event.value = MockEventRepository.eventMock
    }
}