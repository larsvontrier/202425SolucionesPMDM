package com.pepinho.pmdm.burgerbuilder.presentation.screens

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
import com.pepinho.pmdm.burgerbuilder.model.PosicionRecheo
import com.pepinho.pmdm.burgerbuilder.model.Recheo

@Preview
@Composable
private fun ToppingCellPreviewOnLeftHalf() {
    CelaRecheo(
        recheo = Recheo.Queixo,
        posicion = PosicionRecheo.Enriba,
        onClickRecheo = {}
    )
}

@Composable
fun CelaRecheo(
    recheo: Recheo,
    posicion: PosicionRecheo?,
    onClickRecheo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClickRecheo() }
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Checkbox(
            checked = (posicion != null),
            onCheckedChange = { onClickRecheo() }
        )

        Column(
            modifier = Modifier.weight(1f, fill = true)
                .padding(start = 4.dp)
        ) {
            Text(
                text = stringResource(recheo.nome),
                style = MaterialTheme.typography.bodyMedium
            )

            if (posicion != null) {
                Text(
                    text = stringResource(posicion.label),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
