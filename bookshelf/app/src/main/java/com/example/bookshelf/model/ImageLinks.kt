package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail") val smallThumbnail: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null
)
