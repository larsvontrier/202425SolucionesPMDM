package com.pepinho.pmdm.burgerbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.pepinho.pmdm.burgerbuilder.presentation.screens.BurgerBuilderScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BurgerBuilderScreen(
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}