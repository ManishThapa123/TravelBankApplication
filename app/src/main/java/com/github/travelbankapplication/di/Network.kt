package com.github.travelbankapplication.di

import com.github.api.TravelBankAPIInterface
import com.github.api.TravelBankClient
import com.github.api.models.entities.TravelBankAPIResponseItem
import com.github.travelbankapplication.repository.BaseRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Network {

    /**
     * provides instance of api client interface [GithubClient.service]
     */
    @Provides
    @Singleton
    fun provideApiClient(): TravelBankAPIInterface = TravelBankClient.service

    /**
     * provides instance of base repository [BaseRepository]
     */
    @Provides
    @Singleton
    fun providesBaseRepository(
        apiInterface: TravelBankAPIInterface): BaseRepository =
        BaseRepository(apiInterface)


    /**
     * provides instance of adapter which serializes and deserialized
     */
    @Provides
    @Singleton
    fun provideTravelBankApiResponseItemDataAdapter(): JsonAdapter<TravelBankAPIResponseItem> =
        Moshi.Builder().build().adapter(TravelBankAPIResponseItem::class.java)
}