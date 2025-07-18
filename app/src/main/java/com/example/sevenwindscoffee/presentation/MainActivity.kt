package com.example.sevenwindscoffee.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.entities.RequestUser
import androidx.navigation.compose.NavHost
import com.example.sevenwindscoffee.screens.HomeScreen
import com.example.sevenwindscoffee.screens.LocationsScreen
import com.example.sevenwindscoffee.screens.MapScreen
import com.example.sevenwindscoffee.screens.ProductsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

//Не стал выделять в отдельный модуль presentation, чистота от этого, думаю, не пострадает
class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<ViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = RequestUser("Studio", "6667")

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = HomePage.route) {
                composable(route = HomePage.route) {
                    HomeScreen(viewModel, onClick = { navController.navigate(Locations.route) })
                }

                composable(Locations.route) {
                    LocationsScreen(viewModel,
                        onClick = { navController.navigate(Products.route) },
                        onBackPressed = { navController.navigateUp()},
                        toMap = {navController.navigate(Map.route)})
                }
                composable(Products.route) {
                    ProductsScreen(viewModel,
                        onClick = { navController.navigate(Products.route) },
                        onBackPressed = { navController.navigateUp()})
                }

                composable(Map.route) {
                    MapScreen(viewModel,this@MainActivity,
                        onBackPressed = { navController.navigateUp()})
                }
            }
        }
    }
}