package com.pedrosiccha.isoandiso_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.pedrosiccha.ui.navigation.AppNavHost
import com.pedrosiccha.ui.theme.IsoAndiSoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsoAndiSoTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}