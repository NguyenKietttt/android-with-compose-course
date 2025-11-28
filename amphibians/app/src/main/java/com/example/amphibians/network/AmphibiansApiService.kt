package com.example.amphibians.network

import com.example.amphibians.model.AmphibianData
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getListAmphibianData(): List<AmphibianData>
}