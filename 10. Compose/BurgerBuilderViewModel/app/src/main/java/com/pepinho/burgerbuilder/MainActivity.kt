package com.pepinho.burgerbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepinho.burgerbuilder.ui.screens.burgerbuilder.BurgerBuilderScreen
import com.pepinho.burgerbuilder.ui.screens.burgerbuilder.BurgerBuilderViewModel
import com.pepinho.burgerbuilder.ui.theme.BurgerBuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BurgerBuilderTheme {
                val viewModel: BurgerBuilderViewModel = viewModel()
                BurgerBuilderScreen(viewModel = viewModel)
            }
        }
    }
}


