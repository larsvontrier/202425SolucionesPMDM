package com.pepinho.pmdm.burgerbuilder.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pepinho.pmdm.burgerbuilder.model.Burger
import com.pepinho.pmdm.burgerbuilder.model.PosicionRecheo
import com.pepinho.pmdm.burgerbuilder.model.Recheo
import com.pepinho.pmdm.burgerbuilder.R


@Preview
@Composable
private fun PizzaHeroImagePreview() {
    BurgerImage(
        burger = Burger(
            recheos = mapOf(
                Recheo.Queixo to PosicionRecheo.EnribaEAbaixo,
                Recheo.Ketchup to PosicionRecheo.Enriba,
                Recheo.Leituga to PosicionRecheo.Abaixo
            )
        )
    )
}

@Composable
fun BurgerImage(
    burger: Burger,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(R.drawable.burger),
            contentDescription = stringResource(R.string.previsualizacion),
            modifier = Modifier.fillMaxSize()
        )

//        burger.recheos.forEach { (recheo, posicion) ->
//            Image(
//                painter = painterResource(recheo.imaxeSobreBurger),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                alignment = when (posicion) {
//                    PosicionRecheo.Enriba ->  Alignment.TopStart
//                    PosicionRecheo.Abaixo ->  Alignment.TopEnd
//                    PosicionRecheo.EnribaEAbaixo ->  Alignment.Center
//                } as Alignment,
//                modifier = Modifier.focusable(false)
//                    .aspectRatio(when (posicion) {
//                        PosicionRecheo.Enriba, PosicionRecheo.Abaixo -> 0.5f
//                        PosicionRecheo.EnribaEAbaixo -> 1.0f
//                    })
//                    .align(when (posicion) {
//                        PosicionRecheo.Enriba -> Alignment.CenterStart
//                        PosicionRecheo.Abaixo -> Alignment.CenterEnd
//                        PosicionRecheo.EnribaEAbaixo -> Alignment.Center
//                    })
//            )
//        }
    }
}
