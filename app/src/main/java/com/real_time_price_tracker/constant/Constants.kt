package com.real_time_price_tracker.constant


enum class Environment(val baseUrl: String) {
    DEV("wss://ws.postman-echo.com/raw"),
    SIT("wss://ws.postman-echo.com/raw"),
    UAT("wss://ws.postman-echo.com/raw"),
    PROD("wss://ws.postman-echo.com/raw");

    companion object {
        /**
         * Optional: Helper method to get the current environment based on
         * Build Variants or a specific flag.
         */
        fun getEnvironment(envName: String): Environment {
            return values().find { it.name.equals(envName, ignoreCase = true) } ?: DEV
        }
    }
}

/**
 * Global constants for network configuration.
 */
object NetworkConfig {
    // You can manually toggle this or link it to BuildConfig.BUILD_TYPE
    val currentEnvironment = Environment.DEV

    val baseUrl: String
        get() = currentEnvironment.baseUrl
}