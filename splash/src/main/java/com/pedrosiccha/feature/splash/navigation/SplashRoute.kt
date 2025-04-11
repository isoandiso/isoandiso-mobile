package com.pedrosiccha.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pedrosiccha.feature.splash.ui.SplashScreen

const val splashRoute = "splash"

fun NavGraphBuilder.splashScreenRoute(
    onNavigateToLogin: () -> Unit
) {
    composable(route = splashRoute) {
        SplashScreen(onNavigateToLogin = onNavigateToLogin)
    }
}