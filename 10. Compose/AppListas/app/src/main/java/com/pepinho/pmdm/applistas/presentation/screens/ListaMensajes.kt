package com.pepinho.pmdm.applistas.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pepinho.pmdm.applistas.model.Mensaje

@Composable
fun ListaMensajes(mensajes: List<Mensaje>, padding: PaddingValues = PaddingValues()) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(padding)) {
        mensajes.forEach { mensaje ->
            FilaMensaje(mensaje)
        }
    }
}

