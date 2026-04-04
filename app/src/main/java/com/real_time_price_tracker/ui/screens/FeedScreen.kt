package com.real_time_price_tracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.real_time_price_tracker.data.model.StockSymbol
import com.real_time_price_tracker.data.model.Trend
import com.real_time_price_tracker.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(viewModel: StockViewModel, onNavigate: (String) -> Unit) {
    val stocks by viewModel.stocks.collectAsState()
    val connected by viewModel.isConnected.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Tracker") },
                navigationIcon = {
                    Text(if (connected) "🟢" else "🔴", Modifier.padding(start = 16.dp))
                },
                actions = {
                    Button(onClick = { viewModel.toggleFeed() }, modifier = Modifier.padding(end = 8.dp)) {
                        Text(if (connected) "Stop" else "Start")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(stocks, key = { it.symbol }) { stock ->
                StockItem(stock) { onNavigate(stock.symbol) }
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
            }
        }
    }
}

@Composable
fun StockItem(stock: StockSymbol, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stock.symbol, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
        Row {
            Text("$${String.format("%.2f", stock.price)}", fontWeight = FontWeight.Medium)
            Spacer(Modifier.width(8.dp))
            val (icon, color) = when(stock.trend) {
                Trend.UP -> "↑" to Color(0xFF4CAF50)
                Trend.DOWN -> "↓" to Color(0xFFF44336)
                Trend.NEUTRAL -> "" to Color.Gray
            }
            Text(icon, color = color, fontWeight = FontWeight.Bold)
        }
    }
}