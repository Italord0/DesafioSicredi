package com.example.desafiosicredi.ui.features.home

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.desafiosicredi.R
import com.example.desafiosicredi.data.repository.MockEventRepository.Companion.eventMock
import com.example.desafiosicredi.nav.NavRoute
import com.example.desafiosicredi.ui.composables.EventCard
import com.example.desafiosicredi.util.Colors

object HomeRoute : NavRoute<HomeViewModel> {
    override val route: String
        get() = "home"

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel, args: Bundle?) = Home()

}

@Composable
private fun Home(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val events = remember { viewModel.events }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.events))
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(Colors.LightestGray)
            ) {
                LazyColumn {
                    if (viewModel.loading.value) {
                        items(5) {
                            EventCard(
                                event = eventMock,
                                isLoading = true
                            )
                        }
                    } else {
                        itemsIndexed(events.value) { _, event ->
                            EventCard(event = event, onEventClicked = viewModel::onEventClicked)
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun HomePreview() {
    val viewModel = PreviewHomeViewModel()
    Home(viewModel = viewModel)
}