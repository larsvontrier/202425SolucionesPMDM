package com.pepinho.pmdm.burgerbuilder.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepinho.pmdm.burgerbuilder.R
import com.pepinho.pmdm.burgerbuilder.model.Burger
import com.pepinho.pmdm.burgerbuilder.model.Recheo
import java.text.NumberFormat


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BurgerBuilderScreen(
    modifier: Modifier = Modifier
) {
    var burger by rememberSaveable { mutableStateOf(Burger()) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        content = { padding ->
            Column {
                ListaRecheo(
                    burger = burger,
                    onEditPizza = { burger = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                )

                BotonPedido(
                    burger = burger,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    )
}

@Composable
private fun ListaRecheo(
    burger: Burger,
    onEditPizza: (Burger) -> Unit,
    modifier: Modifier = Modifier
) {
    var recheoEngadido by rememberSaveable { mutableStateOf<Recheo?>(null) }

    recheoEngadido?.let { recheo ->
        DialogoPosicionRecheo(
            recheo = recheo,
            onSetPosicionRecheo = { posicion ->
                onEditPizza(burger.conRecheo(recheo, posicion))
            },
            onDismissRequest = {
                recheoEngadido = null
            }
        )
    }

    LazyColumn(
        modifier = modifier
    ) {
//        item {
//            BurgerImage(
//                burger = burger,
//                modifier = Modifier.padding(16.dp)
//            )
//        }

        items(Recheo.values()) { recheo ->
            CelaRecheo(
                recheo = recheo,
                posicion = burger.recheos[recheo],
                onClickRecheo = {
                    recheoEngadido = recheo
                }
            )
        }
    }
}

@Composable
private fun BotonPedido(
    burger: Burger,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick = {
            Toast.makeText(context, R.string.mensaxe_engadido, Toast.LENGTH_LONG)
                .show()
        }
    ) {
        val currencyFormatter = remember { NumberFormat.getCurrencyInstance() }
        val price = currencyFormatter.format(burger.prezo)
        Text(
            text = stringResource(R.string.pedido_button, price)
                .toUpperCase(Locale.current)
        )
    }
}
