package com.github.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TravelBankClient{
    private const val API_PREFIX = "https://run.mocky.io/v3/"
    private const val NETWORK_CALL_TIMEOUT = 60

    /**
     * The Retrofit class generates an implementation of the [TravelBankAPIInterface] interface.
     */
    // Set the custom client when building adapter
    private val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .connectTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_PREFIX)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpBuilder)
        .build()

    var service: TravelBankAPIInterface = retrofit.create(TravelBankAPIInterface::class.java)
}