package ru.eugenerogov.data

import java.util.*

data class Currency(
    val id: UUID = UUID.randomUUID(),
    var currencyPair: String = "",
    var lastPrice: Double = 0.0,
    var _24HoursChange: Float = 0.0F,
) {
}