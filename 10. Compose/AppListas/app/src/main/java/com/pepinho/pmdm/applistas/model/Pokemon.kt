package com.pepinho.pmdm.applistas.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Pokemon(
    val name: String,
    val url: String
)
