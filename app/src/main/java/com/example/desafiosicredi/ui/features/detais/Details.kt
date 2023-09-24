package com.example.desafiosicredi.ui.features.detais

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.desafiosicredi.R
import com.example.desafiosicredi.nav.NavRoute
import com.example.desafiosicredi.ui.composables.BackButton
import com.example.desafiosicredi.util.Colors
import com.skydoves.landscapist.coil.CoilImage

const val KEY_EVENT_ID = "EVENT_ID"


object DetailsRoute : NavRoute<DetailsViewModel> {
    override val route: String
        get() = "details/{$KEY_EVENT_ID}"

    fun getEventId(): String = route.removePrefix("details/")

    @Composable
    override fun viewModel(): DetailsViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DetailsViewModel, args: Bundle?) = Details(bundle = args)
}

@Composable
private fun Details(
    viewModel: DetailsViewModel = hiltViewModel(),
    bundle: Bundle?
) {
    val scaffoldState = rememberScaffoldState()
    val event = remember { viewModel.event }
    val eventId = bundle?.getString(KEY_EVENT_ID) ?: ""

    viewModel.fetchEvent(eventId)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    BackButton(onClick = viewModel::onBackClicked)
                },
                title = {
                    Text(text = stringResource(id = R.string.events_detail))
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                val (eventTitle, eventDescription, eventDate, eventImage) = createRefs()

                CoilImage(
                    modifier = Modifier.constrainAs(eventImage) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                        height = Dimension.value(200.dp)
                    },
                    imageModel = { event.value?.image }
                )
                Text(
                    modifier = Modifier.constrainAs(eventTitle) {
                        top.linkTo(eventImage.bottom, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                    text = event.value?.title ?: "",
                    style = TextStyle.Default.copy(fontSize = 28.sp)
                )
                Text(
                    modifier = Modifier.constrainAs(eventDescription) {
                        top.linkTo(eventTitle.bottom, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                    text = event.value?.description ?: "",
                    style = TextStyle.Default.copy(fontSize = 20.sp)
                )
            }
        }
    )
}