package com.example.bookshelf.data

import com.example.bookshelf.model.BooksResponse
import com.example.bookshelf.network.BookshelfApiService

interface BookshelfRepository {
    suspend fun getBooksResponse(keyword: String) : BooksResponse
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookshelfRepository {
    override suspend fun getBooksResponse(keyword: String): BooksResponse {
        return bookshelfApiService.getBooksResponse(keyword)
    }
}