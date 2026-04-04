package com.real_time_price_tracker.data.model

import androidx.compose.ui.graphics.Color

data class StockSymbol(
    val symbol: String,
    val price: Double = 0.0,
    val prevPrice: Double = 0.0,
    val description: String = "Leading global company in the technology and services sector."
) {
    val trend: Trend = when {
        price > prevPrice -> Trend.UP
        price < prevPrice -> Trend.DOWN
        else -> Trend.NEUTRAL
    }
}

enum class Trend { UP, DOWN, NEUTRAL }
