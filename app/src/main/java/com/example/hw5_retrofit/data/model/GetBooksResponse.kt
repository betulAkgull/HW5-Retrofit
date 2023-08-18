package com.example.hw5_retrofit.data.model


data class GetBooksResponse(
    val books: List<Book>?,
    val message: String?,
    val success: Int?
)