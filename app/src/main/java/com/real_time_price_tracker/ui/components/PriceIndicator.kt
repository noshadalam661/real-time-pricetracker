package com.real_time_price_tracker.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.real_time_price_tracker.data.model.Trend

@Composable
fun PriceIndicator(trend: Trend) {
    val (text, color) = when (trend) {
        Trend.UP -> "↑" to Color(0xFF4CAF50)
        Trend.DOWN -> "↓" to Color(0xFFF44336)
        Trend.NEUTRAL -> "" to Color.Transparent
    }
    Text(text = text, color = color, fontWeight = FontWeight.ExtraBold)
}
