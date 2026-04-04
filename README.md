# Real-Time Price Tracker

A robust Android application built with **Jetpack Compose** that simulates a live stock market feed. 
The app demonstrates real-time data handling using WebSockets with MVVM patterns.

## Feed Screen

<p float="left">
	<img src="https://github.com/noshadalam661/real-time-pricetracker/blob/main/SS/feed.jpeg" width="200"/>
</p>

## Symbol Detail Screen

<p float="left">
	<img src="https://github.com/noshadalam661/real-time-pricetracker/blob/main/SS/feed_detail_1.jpeg" width="200"/>
	<img src="https://github.com/noshadalam661/real-time-pricetracker/blob/main/SS/feed_detail_2.jpeg" width="200"/>
</p>


## 🚀 Features

- **Live Price Feed**: Monitors 25 stock symbols (NVDA, AAPL, GOOG, etc.) with real-time updates.
- **WebSocket Integration**: Connects to `wss://ws.postman-echo.com/raw` to simulate live data traffic.
- **Dynamic UI**:
    - **Feed Screen**: A sorted `LazyColumn` showing symbol names, current prices, and visual indicators (↑/↓) for price changes.
    - **Details Screen**: In-depth view for a selected symbol, maintaining live updates.
- **Feed Control**: Toggle the live stream on/off and monitor connection status (🟢/🔴) directly from the Top Bar.
- **Bonus Implementation**:
    - ✨ **Price Flashing**: Rows flash green on increase and red on decrease for 1 second.
    - 🌓 **Theming**: Full support for Light and Dark modes.

## 🛠 Tech Stack & Architecture

- **UI**: 100% Jetpack Compose.
- **Architecture**: MVVM (Model-View-ViewModel) for clean separation of concerns.
- **Navigation**: Navigation Compose with `NavHost` for screen transitions.
- **Asynchronous Flow**: Kotlin Coroutines and **StateFlow** to manage WebSocket data streams.
- **State Management**: `SavedStateHandle` used in ViewModels to persist navigation arguments.

## 📦 Project Structure

- `ui/`: Contains Compose screens (Feed and Details) and reusable components.
- `navigation/`: Defines the NavHost and destination routes.
- `data/`: Manages the WebSocket connection and repository logic.
- `viewmodel/`: Holds the UI state and business logic, ensuring the WebSocket stream is shared across screens.

## 🛠️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com
