package com.real_time_price_tracker.navigation

sealed class Destinations(val route: String) {
    object Feed : Destinations("feed")
    object Details : Destinations("details/{symbol}") {
        fun createRoute(symbol: String) = "details/$symbol"
    }
}