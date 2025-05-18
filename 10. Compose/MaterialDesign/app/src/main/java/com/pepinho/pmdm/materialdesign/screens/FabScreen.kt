package com.pepinho.pmdm.materialdesign.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabScreen(
    onAddNote: () -> Unit = {}, // acción para añadir nota
    onShare: () -> Unit = {},
    onLocation: () -> Unit = {},
    onCreateItem: () -> Unit = {}
) {
    // Scaffold es el contenedor ideal para situar FABs
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exemplos de FAB") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción de navegación */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú") // Icono hamburguesa
                    }
                }
            )
        },
        floatingActionButton = {
            // Usamos Column para apilar múltiples FABs con espaciado
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. FAB Extendido (principal)
                ExtendedFloatingActionButton(
                    onClick = onCreateItem,
                    icon = { Icon(Icons.Filled.Add, "Engadir") },
                    text = { Text("Crear novo") },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(end = 8.dp)
                )

                // 2. FAB estándar con animación de aparición
                AnimatedVisibility(visible = true) { // Controlar con estado si es necesario
                    FloatingActionButton(
                        onClick = onAddNote,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Filled.Edit, "Nota")
                    }
                }

                // 3. Grupo de FABs secundarios
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // FAB pequeño para compartir
                    SmallFloatingActionButton(
                        onClick = onShare,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Icon(Icons.Filled.Share, "Compartir")
                    }

                    // FAB de ubicación
                    FloatingActionButton(
                        onClick = onLocation,
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Icon(Icons.Filled.LocationOn, "Ubicación")
                    }
                }
            }
        }
    ) { paddingValues ->
        // Contenido principal de la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Contido da pantalla", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

// Versión simplificada con un único el FAB principal
@Composable
fun BasicFabScreen(
    onPrimaryAction: () -> Unit = {}
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onPrimaryAction,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Acción principal") // Icono de añadir
            }
        }
    ) { padding ->
        // Contenido de la pantalla
        Box(Modifier.fillMaxSize()) {
            /* ... */
        }
    }
}