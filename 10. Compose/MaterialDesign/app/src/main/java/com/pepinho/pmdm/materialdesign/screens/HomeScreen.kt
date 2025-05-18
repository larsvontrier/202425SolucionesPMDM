package com.pepinho.pmdm.materialdesign.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var nombre by remember { mutableStateOf("") } // Estado da caixa de texto
    var mensajeMostrado by remember { mutableStateOf(false) } // Estado da mensaxe

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compoñentes Material") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción de navegación */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú") // Icono hamburguesa
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { mensajeMostrado = true }) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Enviar") // El icono de enviar (avión de papel)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Benvido PMDM", style = MaterialTheme.typography.headlineSmall)

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Dime o teu nome") }, // Etiqueta del campo de texto
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { mensajeMostrado = true }, // Al hacer clic, se muestra el mensaje
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Mostrar mensaxe")
            }

            if (mensajeMostrado) {
                Card(
                    elevation = CardDefaults.cardElevation(6.dp), // Elevación de la tarjeta
                    modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red) // Icono de corazón
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ola, $nombre!", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
