package com.example.pregnancyvitalstracker.naviation

import HomePage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootGraph.HOME
    ) {
//        composable(RootGraph.HOME) {
//            HomePage()
//        }
    }
}
