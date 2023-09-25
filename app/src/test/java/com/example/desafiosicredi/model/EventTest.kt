package com.example.desafiosicredi.model

import com.example.desafiosicredi.data.model.Event
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class EventTest {

    private lateinit var event: Event

    @Before
    fun setUp() {
        val date =
            SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).parse("01/01/2023 14:30")
        if (date != null) {
            event = Event(
                people = listOf("Person 1", "Person 2"),
                date = date.time,
                description = "Event Description",
                image = "Event Image",
                longitude = 1.0,
                latitude = 2.0,
                price = 100.0,
                title = "Event Title",
                id = "Event ID"
            )
        }
    }

    @Test
    fun testFormattedDate() {
        val expectedFormattedDate = "01/01/2023 14:30"
        assertEquals(expectedFormattedDate, event.formattedDate)
    }

    @Test
    fun testFormattedPrice() {
        val expectedFormattedPrice = "R$\u00A0100,00"
        assertEquals(expectedFormattedPrice, event.formattedPrice)
    }

    @Test
    fun testDataClassProperties() {
        assertEquals("Event ID", event.id)
        assertEquals("Event Title", event.title)
        assertEquals("Event Image", event.image)
        assertEquals("Event Description", event.description)
        assertEquals(1.0, event.longitude, 0.0)
        assertEquals(2.0, event.latitude, 0.0)
        assertEquals(100.0, event.price, 0.0)
        assertEquals(listOf("Person 1", "Person 2"), event.people)
    }
}
