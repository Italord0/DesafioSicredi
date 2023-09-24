package com.example.desafiosicredi.ui.features.home

import com.example.desafiosicredi.data.repository.MockEventRepository
import com.example.desafiosicredi.nav.MockRouteNavigator

class PreviewHomeViewModel : HomeViewModel(
    MockRouteNavigator(),
    MockEventRepository()
) {
    init {
        events.value = MockEventRepository.eventsListMock
    }
}