package com.github.api

import com.github.api.models.entities.TravelBankAPIResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface TravelBankAPIInterface {

    @GET("178cbbee-c634-4a51-afb8-dcd75c190d29")
    suspend fun getExpenseList(): Response<List<TravelBankAPIResponseItem>>
}