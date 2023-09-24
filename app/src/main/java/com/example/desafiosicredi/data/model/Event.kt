package com.example.desafiosicredi.data.model

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
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
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return format.format(date)
        }

    val formattedPrice: String
        get() {
            val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            currencyFormat.currency = Currency.getInstance("BRL")
            return currencyFormat.format(price)
        }
}
