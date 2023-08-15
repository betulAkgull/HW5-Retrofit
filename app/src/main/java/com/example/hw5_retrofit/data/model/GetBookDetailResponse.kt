package com.example.hw5_retrofit.data.model



data class GetBookDetailResponse(
    val book: Book?,
    val message: String?,
    val success: Int?
)