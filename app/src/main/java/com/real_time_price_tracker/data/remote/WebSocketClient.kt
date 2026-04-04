package com.real_time_price_tracker.data.remote

import okhttp3.*
import kotlinx.coroutines.flow.MutableSharedFlow

class WebSocketClient(private val url: String) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connect(listener: WebSocketListener): WebSocket {
        val request = Request.Builder().url(url).build()
        val ws = client.newWebSocket(request, listener)
        this.webSocket = ws
        return ws
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Normal Closure")
    }
}