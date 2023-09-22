package com.example.desafiosicredi.data.model

import java.util.Date

data class Event(
    val people: List<String>,
    val date: Date,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
)
