package com.example.desafiosicredi.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Event(
    val people: List<String>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
) {
    val formattedDate: String
        get() {
            val date = Date(date)
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return format.format(date)
        }
}
