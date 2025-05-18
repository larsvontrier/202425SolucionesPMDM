package com.pepinho.pmdm.pokemonnav.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var darkMode by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(true) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Configuración", style = MaterialTheme.typography.headlineMedium)

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Modo oscuro", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = it }
                )
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text("Notificaciones", style = MaterialTheme.typography.titleMedium)
                Switch(
                    checked = notifications,
                    onCheckedChange = { notifications = it }
                )
            }
        }
    }
}