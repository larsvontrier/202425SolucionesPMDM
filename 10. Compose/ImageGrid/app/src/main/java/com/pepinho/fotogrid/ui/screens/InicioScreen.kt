
package com.pepinho.fotogrid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pepinho.fotogrid.R
import com.pepinho.fotogrid.model.PicsumPhoto
import com.pepinho.fotogrid.ui.PicsumUiState
import com.pepinho.fotogrid.ui.theme.FotoGridTheme

@Composable
fun InicioScreen(
    picsumUiState: PicsumUiState,
    accionReintento: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (picsumUiState) {
        is PicsumUiState.Loading -> CargandoScreen(modifier = modifier.fillMaxSize())
        is PicsumUiState.Success -> FotoGridScreenEscalonada(
//        is PicsumUiState.Success -> FotoGridScreen(
            picsumUiState.photos, contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )
        is PicsumUiState.Error -> ErroScreen(accionReintento, modifier = modifier.fillMaxSize())
    }
}

/**
 * La pantalla de inicio muestra el mensaje de carga.
 */
@Composable
fun CargandoScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.cargando)
    )
}

/**
 * La pantalla de inicio muestra el mensaje de error con el botón de reintento.
 */
@Composable
fun ErroScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.erro_carga), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.reintentar))
        }
    }
}

/**
 * La pantalla de inicio muestra el grid de fotos.
 */
@Composable
fun FotoGridScreen(
    fotos: List<PicsumPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        items(items = fotos, key = { foto -> foto.id }) { foto ->
            CardFoto(
                foto,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}


/**
 * La pantalla de inicio muestra el grid de fotos.
 */
@Composable
fun FotoGridScreenEscalonada(
    fotos: List<PicsumPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(100.dp), // Número de columnas adaptativas. Quedarán unas 3 columnas
        verticalItemSpacing = 8.dp,
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = fotos, key = { foto -> foto.id }) { foto ->
            CardFoto(
                foto,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth() // Permite que el ancho sea adaptable
//                    .aspectRatio(1.5f) //  Este modificador fuerza a que cada
            //                    elemento tenga una relación de aspecto fija
            //                    (en este caso, 1.5:1), lo que hace que todos los
            //                    elementos tengan la misma altura independientemente
            //                    de su contenido
            )
        }
    }
}




@Composable
fun CardFoto(foto: PicsumPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage( // Coil disponible en la librería de Compose
            model = ImageRequest.Builder(context = LocalContext.current).data(foto.url)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image), // Imagen de error
            placeholder = painterResource(R.drawable.loading_img), // Mientras carga
            contentDescription = stringResource(R.string.descripcion_foto),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CargandoScreenPreview() {
    FotoGridTheme {
        CargandoScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErroScreenPreview() {
    FotoGridTheme {
        ErroScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun FotoGridScreenPreview() {
    FotoGridTheme {
        val mockData = List(10) { PicsumPhoto("$it", "", "",
            width = 100, height = 100) }
        FotoGridScreen(mockData)
    }
}
