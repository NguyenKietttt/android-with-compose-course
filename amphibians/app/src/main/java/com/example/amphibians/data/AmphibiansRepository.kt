package com.example.amphibians.data

import com.example.amphibians.model.AmphibianData
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getListAmphibianData(): List<AmphibianData>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getListAmphibianData(): List<AmphibianData> {
        return amphibiansApiService.getListAmphibianData()
    }
}