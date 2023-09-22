package com.example.desafiosicredi.ui.features.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.nav.NavRoute

object HomeRoute : NavRoute<HomeViewModel> {
    override val route: String
        get() = "home/"

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) = Home(viewModel::onEventClicked)

}

@Composable
private fun Home(
    onEventClicked: (event: Event) -> Unit,
) {

}