package com.example.desafiosicredi.ui.features.detais

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.desafiosicredi.R
import com.example.desafiosicredi.nav.NavRoute
import com.example.desafiosicredi.ui.composables.BackButton
import com.example.desafiosicredi.ui.composables.CheckInFormComposable
import com.example.desafiosicredi.ui.composables.MessageDialog
import com.example.desafiosicredi.util.extensions.shimmerEffect
import com.skydoves.landscapist.coil.CoilImage

const val KEY_EVENT_ID = "EVENT_ID"


object DetailsRoute : NavRoute<DetailsViewModel> {
    override val route: String
        get() = "details/{$KEY_EVENT_ID}"

    @Composable
    override fun viewModel(): DetailsViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DetailsViewModel, args: Bundle?) = Details(bundle = args)
}

@Composable
private fun Details(
    viewModel: DetailsViewModel = hiltViewModel(), bundle: Bundle?
) {
    val scaffoldState = rememberScaffoldState()
    val event = remember { viewModel.event }
    val eventId = bundle?.getString(KEY_EVENT_ID) ?: ""

    LaunchedEffect(eventId) {
        viewModel.fetchEvent(eventId)
    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(navigationIcon = {
            BackButton(onClick = viewModel::onBackClicked)
        }, title = {
            Text(text = stringResource(id = R.string.events_detail))
        }, backgroundColor = Color.White, contentColor = Color.Black
        )
    }, content = { paddingValues ->
        when (viewModel.loading.value) {
            true -> {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(vertical = 8.dp)
                            .shimmerEffect(true, cornerRadius = 0.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .shimmerEffect(true, cornerRadius = 30.dp)
                    )
                }
            }

            false -> {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    val (eventTitle, eventDescription, eventDate, eventPrice, divider, eventImage, checkInForm) = createRefs()

                    CoilImage(modifier = Modifier.constrainAs(eventImage) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                        height = Dimension.value(200.dp)
                    }, imageModel = { event.value?.image })
                    Text(
                        modifier = Modifier.constrainAs(eventTitle) {
                            top.linkTo(eventImage.bottom, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            width = Dimension.fillToConstraints
                        },
                        text = event.value?.title ?: "",
                        fontSize = 28.sp
                    )

                    Text(
                        modifier = Modifier.constrainAs(eventDate) {
                            top.linkTo(eventTitle.bottom, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            width = Dimension.wrapContent
                        },
                        text = event.value?.formattedDate ?: "",
                        fontSize = 24.sp
                    )

                    Text(
                        modifier = Modifier.constrainAs(eventPrice) {
                            top.linkTo(eventTitle.bottom, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                            width = Dimension.wrapContent
                        },
                        text = event.value?.formattedPrice ?: "",
                        fontSize = 24.sp
                    )

                    Text(
                        modifier = Modifier.constrainAs(eventDescription) {
                            top.linkTo(eventDate.bottom, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            width = Dimension.fillToConstraints
                        },
                        text = event.value?.description ?: "",
                        fontSize = 20.sp
                    )

                    Divider(
                        modifier = Modifier.constrainAs(divider) {
                            top.linkTo(eventDescription.bottom, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                            width = Dimension.fillToConstraints
                        }
                    )

                    CheckInFormComposable(
                        modifier = Modifier.constrainAs(checkInForm) {
                            top.linkTo(divider.bottom, 16.dp)
                            start.linkTo(parent.start, 16.dp)
                            end.linkTo(parent.end, 16.dp)
                        },
                        onFormCompleted = { name, email ->
                            viewModel.doCheckIn(name, email, event.value?.id ?: "")
                        }
                    )
                }
                if (viewModel.showCheckInDialog.value) {
                    MessageDialog(
                        title = stringResource(id = R.string.alert),
                        message = viewModel.checkInResult.value
                    ) {
                        viewModel.hideCheckInDialog()
                    }
                }
            }
        }

    })
}

@Preview
@Composable
fun DetailsPreview() {
    val viewModel = PreviewDetailsViewModel()
    Details(viewModel = viewModel, bundle = Bundle())
}