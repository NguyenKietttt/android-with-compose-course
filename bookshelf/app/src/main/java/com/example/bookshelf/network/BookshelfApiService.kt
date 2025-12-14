package com.example.bookshelf.network

import com.example.bookshelf.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("volumes")
    suspend fun getBooksResponse(
        @Query("q") keyword: String,
        @Query("projection") projection: String = "lite",
    ): BooksResponse
}