package com.example.marsphotos.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 *    "id": "102",
 *     "author": "Ben Moore",
 *     "width": 4320,
 *     "height": 3240,
 *     "url": "https://unsplash.com/photos/pJILiyPdrXI",
 *     "download_url": "https://picsum.photos/id/102/4320/3240"
 *   }
 */
@Serializable
data class PicsumPhoto (
    val id: String,
    val author: String,
    @SerialName(value = "download_url")
    val url: String,
    val width: Int,
    val height: Int)