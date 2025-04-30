package com.pepinho.pmdm.composeud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pepinho.pmdm.composeud.model.Filosofo
import com.pepinho.pmdm.composeud.ui.theme.ComposeUDTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUDTheme {
                Scaffold(modifier = Modifier.padding(10.dp)) { innerPadding ->
                    // Queremos que el contador incremente el número de clicks
                    // y que el texto cambie al hacer click
                    // en el botón

//                    var clicks  by remember { mutableStateOf(0) }
//                    Contador(clicks) {
//                        clicks++
//                    }


                    val filosofo = Filosofo(
                    "Wittgenstein",
                    image = ImageBitmap.imageResource(id = R.drawable.wittgenstein),
                    libro = "Tractatus Logico-Philosophicus"
                    )
                    // 1. Ejemplo básico de dos texto sobreimpuesto
//                    FilosofoCard() // 1. Sobre impuesto
                    // 2. Ejemplo básico de dos textos en columna
//                    FilosofoCardColumn() // 2. En columna

                    Column (modifier = Modifier.padding(innerPadding)){


                        // 3. Ejemplo de dos textos en columna Con imagen a la izquierda
//                        FilosofoCardRow(
//                            filosofo = filosofo
//                        )
//
                        // Le pasamos el modificador para que se pueda desplazar
//                        FilosofoCardRow(
//                            filosofo = filosofo,
//                            modifier = Modifier
//                                .offset(x = 16.dp)
//                        )

                        // 4. Ejemplo con Box, una imagen y un texto
//                        FilosofoCardBox(
//                            filosofo = filosofo,
//                            tamanhoImagen = 300
//                        )

                        // 5. Ejemplo con Box, una imagen y un texto
                        //   y un Icono en la parte superior izquierda
//                        FilosofoCardBoxIcon(
//                            filosofo = filosofo
//                        )


                        // 6. Ejemplo con alineamiento y disposición de elementos
//                        FilosofoCardAlign(
//                            filosofo = filosofo
//                        )

                        // 7. Ejemplo de LazyColumn y offset variable que cambia con cada clic
//                        EjemploLazyColumn()

                        // 8. Ejemplo con Box y MatchParentSize
//                        MatchParentSizeFilosofo(
//                            filosofo = filosofo
//                        )

                        // 9. Ejemplo de filas y columnas scrollables
//                        EjemploScrollable()

                        // 10. Ejemplo de desplazamiento suave y estado de scroll
//                        EstadoScroll()

                        // 11. Detectar gestos de desplazamiento sin mover contenido
//                        ExemploScrollableEstado()


                        // 12. Ejemplo: el contenido se mueve manualmente con `scrollable` y `offset`

//                        EjemploScrollContenido()

                        // 13. Ejemplo Scrollable Horizontal con Offset
                        ScrollableHorizontalConOffSet()
                    }


                }
            }
        }
    }
}

/*
1. Ejemplo básico de dos texto sobreimpuesto
 */
@Composable
fun FilosofoCard() {
    Text("Wittgenstein", style = MaterialTheme.typography.headlineLarge)
    Text("Tractatus Logico-Philosophicus")
}

/*
2. Ejemplo básico de dos textos en columna
 */
@Composable
fun FilosofoCardColumn() {
    Column {
        Text("Wittgenstein", style = MaterialTheme.typography.headlineLarge)
        Text("Tractatus Logico-Philosophicus")
    }
}

/*
3. Ejemplo de dos textos en columna
   y una imagen a la izquierda con Fila y Column
 */
@Composable
fun FilosofoCardRow(filosofo: Filosofo, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(bitmap = filosofo.image, contentDescription = "Imagen del filósofo",
            Modifier.size(80.dp).padding(10.dp).clip(CircleShape))
        Column {
            Text(filosofo.nome, style = MaterialTheme.typography.headlineLarge)
            Text(filosofo.libro, modifier)
        }
    }
}

/*
4. Ejemplo de dos textos en columna
   y una imagen a la izquierda con Fila y Column
   y un Box para superponer la imagen
 */
@Composable
fun FilosofoCardBox(filosofo: Filosofo, tamanhoImagen: Int = 300) {
    val context = LocalContext.current
    Box {
        Image(bitmap = filosofo.image, contentDescription = "Imagen del filósofo",
            Modifier.size(tamanhoImagen.dp).clickable( onClick = {
                Toast.makeText(context, "Has hecho clic", Toast.LENGTH_LONG).show()
            }
            ).padding(40.dp).clip(CircleShape).alpha(0.5f))
        Text(filosofo.nome,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(10.dp).align(Alignment.BottomEnd))
    }
}

/*
5. Ejemplo con Box, una imagen y un texto
   y un Icono en la parte superior izquierda
 */
@Composable
fun FilosofoCardBoxIcon(filosofo: Filosofo) {
    Box {
        Image(bitmap = filosofo.image, contentDescription = "Imagen del filósofo",
            Modifier.size(80.dp).clip(CircleShape).alpha(0.8f))
        Icon(Icons.Outlined.Favorite, contentDescription = "Favorito",
            modifier = Modifier.align(Alignment.TopStart))
    }
}

/*
6. Ejemplo con alineamiento y disposición de elementos
 */
@Composable
fun FilosofoCardAlign(filosofo: Filosofo) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End) {
        Image(bitmap = filosofo.image, contentDescription = "Imagen del filósofo",
            Modifier.size(80.dp).padding(10.dp).clip(CircleShape))
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            Text(filosofo.nome, style = MaterialTheme.typography.headlineLarge)
            Text(filosofo.libro)
        }
    }
}

/*
 * 7. Ejemplo de LazyColumn y offset variable que cambia con cada clic
 */
@Composable
fun EjemploLazyColumn() {
    var offset by remember { mutableStateOf(0) }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(100) { index ->
            Text(
                text = "Item #$index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
//                    .offset { IntOffset(index*10, index * 20) } // Desplazamiento dinámico
                    .clickable { offset += 20 }
                    .offset { IntOffset(offset, 0) } // en cada clic cambia el offset en x
            )
        }
    }
}

/*
8. Ejemplo con Box y MatchParentSize
 */

@Composable
fun MatchParentSizeFilosofo(filosofo: Filosofo) {
    Box {
        Spacer(
            Modifier.matchParentSize()
//                .fillMaxSize()
                .alpha(0.2f)
                .background(Color.Green)
        )
        FilosofoCardAlign(filosofo)
    }
}


/*
 * 9. Ejemplo de filas y columnas scrollables
 */
@Composable
fun EjemploScrollable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            repeat(10) {
                miTarjeta(it)
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            repeat(16) {
                miTarjeta(it)
            }
        }
    }
}

@Composable
fun miTarjeta(numero: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
//        border = BorderStroke(1.dp, Color.DarkGray),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(34.dp)
    ) {
        Text(
            text = "Elemento $numero",
            modifier = Modifier.padding(20.dp)
        )
    }
}

/**
 * 10. Ejemplo de desplazamiento suave y estado de scroll
 */
@Composable
private fun EstadoScroll() {
    // Desplazarse suavemente 100px en la primera composición
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        delay(2000)
        state.animateScrollTo(200) // Desplazarse suavemente a 100px. Debe ejecutarse en un coroutine
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 8.dp)
            .verticalScroll(state)
    ) {
        repeat(10) {
            Text("Elemento $it", modifier = Modifier.padding(2.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge)
        }
    }
}

/**
 * 11. Detectar gestos de desplazamiento sin mover contenido
 */

@Composable
private fun ExemploScrollableEstado() {
    // Estado que guarda el desplazamiento
    var offset by remember { mutableStateOf(0f) }

    Box(
        Modifier
//            .size(150.dp) // tamaño fijo del contenedor
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical, // desplazamiento vertical
                state = rememberScrollableState { delta ->
                    offset += delta // actualiza el estado con el delta del gesto
                    delta // retorna cuánto del desplazamiento fue consumido
                }
            )
            .background(Color.LightGray), // fondo gris claro
        contentAlignment = Alignment.Center
    ) {
        // Muestra el valor actual del desplazamiento
        Text(offset.toString())
    }
}

/**
 * 12. Ejemplo: el contenido se mueve manualmente con `scrollable` y `offset`
 */
@Composable
fun EjemploScrollContenido() {
    // Estado que guarda el desplazamiento vertical
    var offset by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    // Limita el desplazamiento para no salir de los márgenes
                    val newOffset = (offset + delta).coerceIn(-300f, 0f)
                    val consumed = newOffset - offset
                    offset = newOffset
                    consumed
                }
            )
            .clipToBounds(), // asegura que el contenido que se desplaza no se desborde
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .offset { IntOffset(0, offset.toInt()) } // desplazamiento aplicado manualmente
                .padding(16.dp)
        ) {
            repeat(10) {
                Text("Elemento #$it", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

/**
 * 13. Ejemplo Scrollable Horizontal con Offset
 */
@Composable
fun ScrollableHorizontalConOffSet() {
    // Estado que guarda el desplazamiento horizontal
    var offset by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 150.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
            .scrollable(
                orientation = Orientation.Horizontal,
                state = rememberScrollableState { delta ->
                    // Limita el desplazamiento horizontal para no exceder un rango definido
                    val newOffset = (offset + delta).coerceIn(-300f, 0f)
                    val consumed = newOffset - offset
                    offset = newOffset
                    consumed // retorna cuánto del desplazamiento fue consumido
                }
            )
            .clipToBounds(), // evita que el contenido desbordado se muestre fuera del contenedor
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .offset { IntOffset(offset.toInt(), 0) } // aplica el desplazamiento horizontal
                .padding(16.dp)
        ) {
            // Se crean 10 elementos de ejemplo
            repeat(10) {
                Text("Elemento #$it", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

/*
 * 0. Ejemplo de un botón que cuenta los clics
 */

@Composable
fun Contador(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("He sido clicado $clicks veces")
    }
}








