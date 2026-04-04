package com.real_time_price_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.real_time_price_tracker.ui.theme.RealTimePriceTrackerTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.real_time_price_tracker.navigation.NavGraph
import com.real_time_price_tracker.ui.theme.PriceTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PriceTrackerTheme {
                NavGraph()
            }
        }
    }
}