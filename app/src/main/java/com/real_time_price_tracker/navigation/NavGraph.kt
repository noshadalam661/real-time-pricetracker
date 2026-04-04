package com.real_time_price_tracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.createGraph
import androidx.lifecycle.createSavedStateHandle
import com.real_time_price_tracker.data.repository.StockRepository
import com.real_time_price_tracker.ui.screens.DetailsScreen
import com.real_time_price_tracker.ui.screens.FeedScreen
import com.real_time_price_tracker.viewmodel.StockViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    // 1. Create a Factory to tell Android how to build your ViewModel
    val stockViewModel: StockViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val repository = StockRepository()
                StockViewModel(repository, savedStateHandle)
            }
        }
    )

    NavHost(navController = navController, startDestination = Destinations.Feed.route) {
        composable(Destinations.Feed.route) {
            FeedScreen(stockViewModel) { symbol ->
                navController.navigate(Destinations.Details.createRoute(symbol))
            }
        }
        composable(
            route = Destinations.Details.route,
            arguments = listOf(navArgument("symbol") { type = NavType.StringType })
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol")
            DetailsScreen(symbol, stockViewModel) {
                navController.popBackStack()
            }
        }
    }
}