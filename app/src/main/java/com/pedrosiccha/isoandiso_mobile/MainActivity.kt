package com.pedrosiccha.isoandiso_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.pedrosiccha.isoandiso_mobile.navigation.AppNavHost
import com.pedrosiccha.ui.theme.IsoandisomobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsoandisomobileTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}