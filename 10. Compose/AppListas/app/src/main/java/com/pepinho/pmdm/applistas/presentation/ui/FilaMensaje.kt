package com.pepinho.pmdm.applistas.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pepinho.pmdm.applistas.model.Mensaje

@Composable
fun FilaMensaje(mensaje: Mensaje) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(mensaje.contenido)
        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible
        Text(mensaje.fecha.toString())
    }
}