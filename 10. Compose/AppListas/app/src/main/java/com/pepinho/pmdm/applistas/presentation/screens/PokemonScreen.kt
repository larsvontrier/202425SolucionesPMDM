package com.pepinho.pmdm.applistas.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pepinho.pmdm.applistas.R
import com.pepinho.pmdm.applistas.api.Pokemon

@Composable
fun GaleriaFotosScreen(
    viewModel: PokemonViewModel = viewModel()
) {
    val galleryItems by viewModel.galleryItems.collectAsState()

    when {
        galleryItems.isEmpty() -> LoadingScreen()
        else -> GalleryGrid(items = galleryItems)
    }
}


@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun GalleryGrid(items: List<Pokemon>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(items) { item ->
            GalleryItem(item)
        }
    }
}

@Composable
fun GalleryItem(item: Pokemon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Imagen con Coil
            AsyncImage(
                model = item.url,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.error_image),
            )

            // Texto descriptivo
            Text(
                text = item.name,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}