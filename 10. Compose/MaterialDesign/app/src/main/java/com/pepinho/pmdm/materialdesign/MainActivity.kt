package com.pepinho.pmdm.materialdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.pepinho.pmdm.materialdesign.screens.AppBarsScreen
import com.pepinho.pmdm.materialdesign.screens.BotonsScreen
import com.pepinho.pmdm.materialdesign.screens.FabScreen
import com.pepinho.pmdm.materialdesign.screens.HomeScreen
import com.pepinho.pmdm.materialdesign.ui.theme.MaterialDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialDesignTheme {
//                val navController = rememberNavController()
//
//                NavHost(
//                    navController = navController,
//                    startDestination = "home"
//                ) {
                // Aquí puedes definir tus destinos de navegación
//                     composable("home") { HomeScreen(navController) }
                // composable("botons") { BotonsScreen() }
                // composable("fab") { FabScreen() }
//            }

//                HomeScreen()
//                BotonsScreen()
//                FabScreen()
                AppBarsScreen()
            }

        }
    }
}

