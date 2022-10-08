package com.github.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TravelBankAPIResponseItem(
    @Json(name = "amount")
    var amount: Double?,
    @Json(name = "attachments")
    var attachments: List<Attachment>?,
    @Json(name = "currencyCode")
    var currencyCode: String?,
    @Json(name = "date")
    var date: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "expenseVenueTitle")
    var expenseVenueTitle: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "tripBudgetCategory")
    var tripBudgetCategory: String?,
    @Json(name = "type")
    var type: String?
)