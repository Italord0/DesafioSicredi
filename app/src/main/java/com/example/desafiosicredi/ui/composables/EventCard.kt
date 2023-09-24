package com.example.desafiosicredi.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.desafiosicredi.R
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.util.extensions.shimmerEffect
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun EventCard(
    event: Event,
    onEventClicked: (Event) -> Unit = { },
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onEventClicked(event) }
                .shadow(4.dp)
                .shimmerEffect(isLoading, cornerRadius = 10.dp),
            elevation = 4.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CoilImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    imageModel = { event.image },
                    previewPlaceholder = R.drawable.ic_launcher_foreground,
                    failure = { ImagePlaceholder() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = event.title)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = event.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Data: ${event.formattedDate}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = event.formattedPrice)
            }
        }
    }
}

@Preview
@Composable
fun EventCardPreview() {
    val event = Event(
        people = emptyList(),
        date = System.currentTimeMillis(),
        description = "Evento exemplo",
        image = "",
        longitude = 0.0,
        latitude = 0.0,
        price = 49.90,
        title = "Título do evento",
        id = "1"
    )

    EventCard(event = event, onEventClicked = { })
}

@Preview
@Composable
fun EventCardLoadingPreview() {
    val event = Event(
        people = emptyList(),
        date = System.currentTimeMillis(),
        description = "Evento exemplo",
        image = "",
        longitude = 0.0,
        latitude = 0.0,
        price = 49.90,
        title = "Título do evento",
        id = "1"
    )

    EventCard(event = event, onEventClicked = { }, isLoading = true)
}
