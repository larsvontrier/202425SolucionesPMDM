package com.pepinho.pmdm.applistas.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val fotos: FotoResponse
)
