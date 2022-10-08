package com.github.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Attachment(
    @Json(name = "filename")
    var filename: String?,
    @Json(name = "hash")
    var hash: String?,
    @Json(name = "_id")
    var id: String?,
    @Json(name = "mime")
    var mime: String?,
    @Json(name = "original")
    var original: String?,
    @Json(name = "size")
    var size: Int?,
    @Json(name = "thumbnails")
    var thumbnails: Thumbnails?,
    @Json(name = "userId")
    var userId: String?
)