package com.pepinho.pokemonapi.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Pokemon(
    val name: String,
    val url: String
)
