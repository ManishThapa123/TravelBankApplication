package com.github.travelbankapplication.repository

import com.github.api.TravelBankAPIInterface
import com.github.api.models.entities.TravelBankAPIResponseItem
import retrofit2.Response
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val apiService: TravelBankAPIInterface
){
    suspend fun getExpenseList(): Response<List<TravelBankAPIResponseItem>> {
        return apiService.getExpenseList()
    }

}