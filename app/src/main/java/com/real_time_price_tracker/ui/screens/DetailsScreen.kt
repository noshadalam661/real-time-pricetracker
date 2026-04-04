package com.real_time_price_tracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.real_time_price_tracker.ui.components.PriceIndicator
import com.real_time_price_tracker.viewmodel.StockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(symbol: String?, viewModel: StockViewModel, onBack: () -> Unit) {
    val stocks by viewModel.stocks.collectAsState()
    val stock = stocks.find { it.symbol == symbol }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(symbol ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stock?.let {
                Text(text = it.symbol, style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${String.format("%.2f", it.price)}",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    PriceIndicator(it.trend)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            } ?: Text("Symbol not found")
        }
    }
}