package com.pepinho.pmdm.materialdesign.screens

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonsScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Botons Material") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción de navegación */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú") // Icono hamburguesa
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Enviar") // El icono de enviar (avión de papel)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BotonRellenoEjemplo(onClick = { /* Acción do botón */ })
            BotonTonalEjemplo(onClick = { /* Acción do botón */ })
            BotonOutlinedEjemplo(onClick = { /* Acción do botón */ })
            BotonElevadoEjemplo(onClick = { /* Acción do botón */ })
            BotonTextoEjemplo(onClick = { /* Acción do botón */ })
            Button(onClick = {  }) {
                Icon(Icons.Default.Check, contentDescription = "Icono")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Aceptar")
            }
        }
    }
}

@Composable
fun BotonRellenoEjemplo(onClick: () -> Unit, texto: String = "Confirmar (Button)") {
    Button(onClick = { onClick() }) {
        Text(texto)
    }
}

@Composable
fun BotonTonalEjemplo(onClick: () -> Unit, texto: String = "Añadir (FilledTonalButton)") {
    FilledTonalButton(onClick = { onClick() }) {
        Text(texto)
    }
}

@Composable
fun BotonOutlinedEjemplo(onClick: () -> Unit, texto: String = "Cancelar (OutlinedButton)") {
    OutlinedButton(onClick = { onClick() }) {
        Text(texto)
    }
}

@Composable
fun BotonElevadoEjemplo(onClick: () -> Unit, texto: String = "Comprar (ElevatedButton)") {
    ElevatedButton(onClick = { onClick() }) {
        Text(texto)
    }
}

@Composable
fun BotonTextoEjemplo(onClick: () -> Unit, texto: String = "Ver más (TextButton)") {
    TextButton(onClick = { onClick() }) {
        Text(texto)
    }
}
