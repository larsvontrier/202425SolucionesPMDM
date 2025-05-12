package com.pepinho.burgerbuilder.ui.screens.burgerbuilder

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepinho.burgerbuilder.R
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.ui.BurgerIngredient
import com.pepinho.pmdm.burgerbuilder.model.Hamburguesa
import java.text.NumberFormat


@Preview
@Composable
fun BurgerBuilderScreen(
    modifier: Modifier = Modifier,
    viewModel: BurgerBuilderViewModel = viewModel(),
) {
    // Puede usarse collectAsState, pero es mejor vincular el ciclo de vida, para evitar fugas de memoria
    val burger by viewModel.burger.collectAsStateWithLifecycle()
    val ingredientes = viewModel.ingredientes  // Obtiene la lista de ingredientes desde el ViewModel

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (
            modifier = modifier.padding(innerPadding)
        ) {
            ListaIngredientes (
                ingredientes = ingredientes,  // Pasa la lista al componente
                burger = burger,
                onToggleIngrediente = { ingrediente ->
                    viewModel.toggleIngrediente(ingrediente)
                },
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
    ingredientes: List<Ingrediente>,  // Recibe la lista desde el ViewModel
    burger: Hamburguesa,
    onToggleIngrediente: (Ingrediente) -> Unit, // Cambia el nombre del parámetro para que sea más claro
    modifier: Modifier = Modifier,
) {

    LazyColumn(modifier = modifier) {
        items(ingredientes) { ingrediente ->
            BurgerIngredient(
                ingrediente = ingrediente,
                posicion = burger.ingredientes[ingrediente],
                onClickIngrediente = { onToggleIngrediente(ingrediente) }
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
