package com.pepinho.burgerbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pepinho.burgerbuilder.ui.screens.burgerbuilder.BurgerBuilderScreen
import com.pepinho.burgerbuilder.ui.theme.BurgerBuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BurgerBuilderTheme {
                    BurgerBuilderScreen()
            }
        }
    }
}


