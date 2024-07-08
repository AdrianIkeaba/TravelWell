package com.ghostdev.travelwell.data.api

import com.ghostdev.travelwell.BuildConfig
import com.ghostdev.travelwell.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("organization_id") organizationId: String,
        @Query("reverse_sort") reverseSort: Boolean,
        @Query("Appid") appId: String,
        @Query("Apikey") apiKey: String = BuildConfig.apiKey
    ): Response<ProductResponse>
}