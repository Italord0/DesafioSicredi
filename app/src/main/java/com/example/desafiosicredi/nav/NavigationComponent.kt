package com.example.desafiosicredi.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.desafiosicredi.ui.features.home.HomeRoute

@Composable
fun NavigationComponent(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = HomeRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        // routes
        HomeRoute.composable(this, navHostController)
    }
}