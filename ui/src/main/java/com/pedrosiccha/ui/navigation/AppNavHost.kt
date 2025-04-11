package com.pedrosiccha.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pedrosiccha.feature.splash.navigation.splashRoute
import com.pedrosiccha.feature.splash.navigation.splashScreenRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = splashRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        splashScreenRoute(
            onNavigateToLogin = {
                // TODO: reemplazar con loginRoute cuando esté implementado
                // navController.navigate(loginRoute) {
                //     popUpTo(splashRoute) { inclusive = true }
                // }
            }
        )
    }
}