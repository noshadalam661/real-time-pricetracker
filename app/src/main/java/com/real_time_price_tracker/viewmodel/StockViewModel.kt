package com.real_time_price_tracker.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.real_time_price_tracker.data.model.StockSymbol
import com.real_time_price_tracker.data.repository.StockRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class StockViewModel(
    private val repository: StockRepository = StockRepository(),
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val symbols = listOf(
        "AAPL", "NVDA", "GOOG", "TSLA", "MSFT", "AMZN", "META", "BRK.B", "UNH", "V",
        "JNJ", "WMT", "JPM", "PG", "MA", "LLY", "CVX", "HD", "ABBV", "ORCL",
        "MRK", "KO", "PEP", "BAC", "AVGO"
    )

    private val _stocks = MutableStateFlow(symbols.map { StockSymbol(it, (150..200).random().toDouble()) })
    val stocks = _stocks.asStateFlow()

    val isConnected = repository.isConnected
    private var simulationJob: Job? = null

    init {
        repository.connect()
        observeSocketUpdates()
        startPriceSimulation()
    }

    fun toggleFeed() {
        if (isConnected.value) {
            simulationJob?.cancel()
            repository.disconnect()
        } else {
            repository.connect()
            startPriceSimulation()
        }
    }

    private fun startPriceSimulation() {
        simulationJob = viewModelScope.launch {
            while (isActive) {
                _stocks.value.forEach { stock ->
                    val change = (-2..2).random().toDouble()
                    val nextPrice = (stock.price + change).coerceAtLeast(1.0)
                    repository.sendUpdate("${stock.symbol}:$nextPrice")
                }
                delay(2000)
            }
        }
    }

    private fun observeSocketUpdates() {
        viewModelScope.launch {
            repository.priceUpdates.collect { message ->
                val parts = message.split(":")
                if (parts.size == 2) {
                    val symbol = parts[0]
                    val price = parts[1].toDoubleOrNull() ?: 0.0
                    updateStockPrice(symbol, price)
                }
            }
        }
    }

    private fun updateStockPrice(symbol: String, newPrice: Double) {
        _stocks.update { currentList ->
            currentList.map {
                if (it.symbol == symbol) it.copy(prevPrice = it.price, price = newPrice)
                else it
            }.sortedByDescending { it.price }
        }
    }
}
