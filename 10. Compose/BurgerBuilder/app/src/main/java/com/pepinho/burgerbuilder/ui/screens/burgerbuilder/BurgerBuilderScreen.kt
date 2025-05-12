package com.pepinho.burgerbuilder.ui.screens.burgerbuilder

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.model.Posicion
import com.pepinho.burgerbuilder.R
import com.pepinho.burgerbuilder.ui.BurgerIngredient
import com.pepinho.pmdm.burgerbuilder.model.Hamburguesa
import java.text.NumberFormat

// Debugger
//private var burger = Hamburguesa(
//    ingredientes = mapOf(
//        Ingrediente.CEBOLA to Posicion.ENRIBA,
//        Ingrediente.MOSTAZA to Posicion.ABAIXO,
//        Ingrediente.KETCHUP to Posicion.ENRIBA,
//        Ingrediente.QUEIXO to Posicion.AMBOS,
//    ),
//    nome = "Hamburguesa de prueba"
//)
//    set(value) {
//        Log.d("BurgerBuilderScreen", "Hamburguesa: $value")
//        field = value
//    }

// Debugger 2
//private var burger by mutableStateOf(
//    Hamburguesa(
//        nome = "Hmaburguesa personalizada"
//    )
//)



@Preview
@Composable
fun BurgerBuilderScreen(
    modifier: Modifier = Modifier,
) {
    var burger by rememberSaveable {
        mutableStateOf(Hamburguesa(nome = "Hamburguesa personalizada"))
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (
            modifier = modifier.padding(innerPadding)
        ) {
            ListaIngredientes (
                burger = burger,
                /*
                Es el nombre implícito del único parámetro de una lambda en Kotlin cuando no se especifica un nombre explícito.
En este contexto, it es el nuevo objeto Hamburguesa que se genera y se pasa desde la función ListaIngredientes al hacer clic en un ingrediente.
                 */
                onClickIngrediente = { burger = it }, // it es el objeto Hamburguesa
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true),
            )
            BotonPedido(
                burger = burger,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }

}

@Composable
private fun ListaIngredientes(
    burger: Hamburguesa,
    onClickIngrediente: (Hamburguesa) -> Unit, //
    modifier: Modifier = Modifier,
) {

    LazyColumn (modifier = modifier){
        items(Ingrediente.entries.toTypedArray()) { ingrediente ->
            BurgerIngredient(
                ingrediente = ingrediente,
                posicion = burger.ingredientes[ingrediente],
                onClickIngrediente = {
                    val isOnBurger = burger.ingredientes[ingrediente] != null
                    onClickIngrediente(burger.conIngrediente(
                        ingrediente = ingrediente,
                        posicion = if(isOnBurger) {
                            null
                        } else {
                            Posicion.ENRIBA
                        }
                    ))
                }
            )
        }
    }
}

@Composable
private fun BotonPedido(
    burger: Hamburguesa, //= Hamburguesa(nome = "Hamburguesa personalizada"),
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = {  },
    ) {
        val monedaFormateada = remember { NumberFormat.getCurrencyInstance() }
        val precio = monedaFormateada.format(burger.prezo)
        Text(
            text = stringResource(R.string.pedido_button, precio)
                .toUpperCase(Locale.current),
        )
    }
}

@Composable
fun MeuComposable() {
    // Este valor será recordado entre recomposiciones
    val valorLembrado = remember { "Ola, Otto!" }
    //  Usa el valor recordado en la UI
    Text(text = valorLembrado)
}

@Composable
fun MeuComposable2() {
    // Este valor será recordado entre recomposiciones
    val valorLembrado = remember { mutableStateOf("Ola, Otto!") }
    //  Usa el valor recordado en la UI
    Text(text = valorLembrado.value)
}