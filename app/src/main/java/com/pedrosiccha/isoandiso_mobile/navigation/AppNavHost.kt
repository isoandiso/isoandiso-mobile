package com.pedrosiccha.isoandiso_mobile.navigation
//Rutas hacia módulos de feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pedrosiccha.feature.login.navigation.LoginRoute
import com.pedrosiccha.feature.login.presentation.screen.LoginScreen
import com.pedrosiccha.feature.splash.ui.SplashScreen
import com.pedrosiccha.feature.splash.navigation.SplashRoute

//import com.pedrosiccha.feature.register.navigation.RegisterRoute
//import com.pedrosiccha.feature.forgotpassword.navigation.ForgotPasswordRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SplashRoute.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // Splash
        composable(route = SplashRoute.route) {
            SplashScreen(
                onNavigateToLogin = { navController.navigate(LoginRoute.route) },
            )
        }

        // 🔐 Login
        composable(route = LoginRoute.route) {
            LoginScreen(
                onLoginSuccess = { /* TODO: Navegar al dashboard */ },
                onRegister = { /*navController.navigate(RegisterRoute.route)*/ },
                onForgotPassword = { /*navController.navigate(ForgotPasswordRoute.route)*/ }
            )
        }

//        // 📝 Register (placeholder)
//        composable(route = RegisterRoute.route) {
//            // Aquí irá tu pantalla de registro real
//            androidx.compose.material3.Text("Pantalla de Registro (placeholder)")
//        }
//
//        // 🔑 Forgot Password (placeholder)
//        composable(route = ForgotPasswordRoute.route) {
//            // Aquí irá tu pantalla de recuperación real
//            androidx.compose.material3.Text("Pantalla de Recuperación de Contraseña (placeholder)")
//        }
    }
}