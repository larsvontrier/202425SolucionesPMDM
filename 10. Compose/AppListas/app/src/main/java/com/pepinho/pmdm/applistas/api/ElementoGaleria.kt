package com.pepinho.pmdm.applistas.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ElementoGaleria(
    val title: String,
    val id: String,
    @Json(name = "url_s") val url: String,
)
