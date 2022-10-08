package com.github.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnails(
    @Json(name = "full")
    var full: String?,
    @Json(name = "list")
    var list: String?
)