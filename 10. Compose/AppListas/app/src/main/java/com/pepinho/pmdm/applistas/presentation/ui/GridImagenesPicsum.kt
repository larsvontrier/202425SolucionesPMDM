package com.pepinho.pmdm.applistas.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pepinho.pmdm.applistas.R

@Composable
fun GridImagenesPicsum(numFotos: Int = 200, paddingValues: PaddingValues = PaddingValues()) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 64.dp), // Minimo de 64dp por celda
        modifier = Modifier.padding(paddingValues) // Evito que se superponga la barra de estado
    ) {
        items(numFotos) { index ->
            AsyncImage(
                model = "https://picsum.photos/200/300?random=$index", // Índice para evitar caché
                contentDescription = "Imagen $index",
                modifier = Modifier.padding(4.dp),
                error = painterResource(id = R.drawable.error_image), // Recurso de error
                placeholder = painterResource(R.drawable.loading_img) // Recurso de carga
            )
        }
    }
}