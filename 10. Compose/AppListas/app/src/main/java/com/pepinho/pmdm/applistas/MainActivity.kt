package com.pepinho.pmdm.applistas

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pepinho.pmdm.applistas.model.Mensaje
import com.pepinho.pmdm.applistas.presentation.screens.ListaMensajes
import com.pepinho.pmdm.applistas.ui.theme.AppListasTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O) // Requiere API 26 o superior para LocalDateTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Lista de mensajes
                    val mensajes = listOf(
                        Mensaje(1, "Hola", LocalDateTime.of(2025, 1, 1, 12, 0)),
                        Mensaje(2, "¿Qué tal?", LocalDateTime.now()),
                        Mensaje(3, "Adiós", LocalDateTime.now())
                    )

                    // 01. Lista de mensajes
//                    ListaMensajes(mensajes = mensajes, padding = innerPadding)

                    // 02. Grid de imágenes de perros

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(20) { index ->
                            AsyncImage(
                                model = "https://picsum.photos/200/300?random=$index", // Índice para evitar caché
                                contentDescription = "Imagen $index",
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
