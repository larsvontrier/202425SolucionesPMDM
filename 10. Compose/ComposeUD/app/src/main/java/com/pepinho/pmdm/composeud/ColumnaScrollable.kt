package com.pepinho.pmdm.composeud

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp





@Composable
fun ColumnaScrollable() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            miTarjeta(numero = it)
        }
        FilaScrollable()
    }
}

@Composable
fun FilaScrollable() {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        repeat(15) {
            miTarjeta(numero = it)
        }
    }
}
