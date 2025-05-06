package com.pepinho.fotogrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pepinho.fotogrid.ui.AppScreen
import com.pepinho.fotogrid.ui.theme.FotoGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            FotoGridTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppScreen()
                }
            }
        }
    }
}
