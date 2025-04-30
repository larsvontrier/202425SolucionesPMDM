package com.pepinho.pmdm.burgerbuilder.presentation.screens


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pepinho.pmdm.burgerbuilder.R
import com.pepinho.pmdm.burgerbuilder.model.*

@Composable
fun DialogoPosicionRecheo(
    recheo: Recheo,
    onSetPosicionRecheo: (placement: PosicionRecheo?) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column {
                val toppingName = stringResource(recheo.nome)
                Text(
                    text = stringResource(R.string.mensaxe_posicion, toppingName),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )

                PosicionRecheo.values().forEach { placement ->
                    OpcionPosicionRecheo(
                        placementName = placement.label,
                        onClick = {
                            onSetPosicionRecheo(placement)
                            onDismissRequest()
                        }
                    )
                }

                OpcionPosicionRecheo(
                    placementName = R.string.posicion_non,
                    onClick = {
                        onSetPosicionRecheo(null)
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@Composable
private fun OpcionPosicionRecheo(
    @StringRes placementName: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(placementName),
            modifier = Modifier.padding(8.dp)
        )
    }
}
