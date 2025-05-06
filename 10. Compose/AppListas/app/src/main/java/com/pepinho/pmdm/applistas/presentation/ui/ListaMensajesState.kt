package com.pepinho.pmdm.applistas.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pepinho.pmdm.applistas.model.Mensaje

@Composable
fun ListaMensajes(mensajes: List<Mensaje>) {
    val listState = rememberLazyListState() //

    LazyColumn(state = listState) {
        items(mensajes) { mensaje ->
            FilaMensaje(mensaje = mensaje)
        }
    }

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }


}

