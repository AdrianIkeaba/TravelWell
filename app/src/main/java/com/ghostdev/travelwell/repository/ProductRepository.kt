package com.ghostdev.travelwell.repository

import android.util.Log
import com.ghostdev.travelwell.data.api.RetrofitInstance
import com.ghostdev.travelwell.data.models.ProductResponse

class ProductRepository {
    private val api = RetrofitInstance.api

    suspend fun getProducts(): ProductResponse? {
        return try {
            val response = api.getProducts(
                organizationId = "8d4bb9640d3449b888db50cd5843aea1",
                reverseSort = false,
                appId = "PS6EP9VW2PED3A4"
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
