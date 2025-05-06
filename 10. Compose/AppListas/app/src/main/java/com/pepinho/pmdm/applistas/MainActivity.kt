package com.pepinho.pmdm.applistas

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepinho.pmdm.applistas.presentation.screens.PokemonScreen
import com.pepinho.pmdm.applistas.presentation.screens.PokemonViewModel
import com.pepinho.pmdm.applistas.presentation.ui.GridImagenesPicsum
import com.pepinho.pmdm.applistas.presentation.theme.AppListasTheme

class MainActivity : ComponentActivity() {
//    val repository = PokemonRepositoryImpl(PokemonApi.getInstance())
//    val viewModel = ViewModelProvider(
//        this,
//        PokemonViewModelFactory(repository)
//    )[PokemonViewModel::class.java]

    @RequiresApi(Build.VERSION_CODES.O) // Requiere API 26 o superior para LocalDateTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->



                    // 01. Lista de mensajes
                    // Lista de mensajes
//                    val mensajes = listOf(
//                        Mensaje(1, "Hola", LocalDateTime.of(2025, 1, 1, 12, 0)),
//                        Mensaje(2, "¿Qué tal?", LocalDateTime.now()),
//                        Mensaje(3, "Adiós", LocalDateTime.now())
//                    )
//                    ListaMensajes(mensajes = mensajes, padding = innerPadding)

                    // 02. Grid de 20 imágenes de picsum
//                    GridImagenesPicsum(numFotos = 300, paddingValues = innerPadding)


                    // 03. Grid de pokemon del API
                    // Uso de viewModel() para obtener el ViewModel.
                    // El ViewModel accede a un objeto factory que se define en el ViewModel
                    // (podría hacerse en una clase separada)
                    val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)
                    PokemonScreen(viewModel, innerPadding) // Como no le pasamos el padding, aparece bajo la barra de estado


                    //
                }
            }
        }
    }


}
