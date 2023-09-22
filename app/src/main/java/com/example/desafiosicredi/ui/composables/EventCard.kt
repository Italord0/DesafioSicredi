package com.example.desafiosicredi.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.desafiosicredi.data.model.Event
import com.example.desafiosicredi.R

@Composable
fun EventCard(
    event: Event,
    onEventClicked: (Event) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onEventClicked(event) }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .shadow(4.dp),
            elevation = 4.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Display event image with content description
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null, // Provide a meaningful description
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Display event title
                Text(
                    text = event.title,
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Display event description
                Text(
                    text = event.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Display event date
                Text(
                    text = "Data: ${event.formattedDate}",
                )

                Spacer(modifier = Modifier.height(8.dp))
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
        price = 0.0,
        title = "Título do evento",
        id = "1"
    )

    EventCard(event = event, onEventClicked = { /* Handle click here */ })
}
