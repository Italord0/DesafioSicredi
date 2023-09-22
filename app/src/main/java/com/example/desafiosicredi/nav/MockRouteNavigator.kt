package com.example.desafiosicredi.nav

import com.example.desafiosicredi.nav.NavigationState
import com.example.desafiosicredi.nav.RouteNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MockRouteNavigator : RouteNavigator {
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        // Mock implementation for testing
    }

    override fun navigateUp() {
        // Mock implementation for testing
    }

    override fun popToRoute(route: String) {
        // Mock implementation for testing
    }

    override fun navigateToRoute(route: String) {
        // Mock implementation for testing
    }
}