package com.pepinho.burgerbuilder.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.model.Posicion
import com.pepinho.pmdm.burgerbuilder.model.Hamburguesa

@Composable
fun BurgerIngredient(
    ingrediente: Ingrediente,
    posicion: Posicion?,
    onClickIngrediente: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("BurgerIngredient", "Ingrediente: $ingrediente, Posicion: $posicion")
    Row(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onClickIngrediente() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = (posicion != null),
            onCheckedChange = { onClickIngrediente() }
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)) {
            Text(
                text = stringResource(ingrediente.nome),
                style = MaterialTheme.typography.bodyLarge
            )
            if (posicion != null) {
                Text(
                    text = stringResource(posicion.nome),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BurgerIngredientPreview() {
    BurgerIngredient(
        ingrediente = Ingrediente.CEBOLA,
        posicion = null,
        onClickIngrediente = {},
    )
}

@Preview
@Composable
private fun BurgerIngredientPreviewConPosicion() {
    BurgerIngredient(
        ingrediente = Ingrediente.CEBOLA,
        posicion = Posicion.ABAIXO,
        onClickIngrediente = {},
    )
}