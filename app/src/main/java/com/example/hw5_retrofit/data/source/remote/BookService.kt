package com.example.hw5_retrofit.data.source.remote

import com.example.hw5_retrofit.common.Constants.Endpoint.GET_BOOKS
import com.example.hw5_retrofit.common.Constants.Endpoint.GET_BOOK_DETAIL
import com.example.hw5_retrofit.data.model.GetBookDetailResponse
import com.example.hw5_retrofit.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface BookService {

    @GET(GET_BOOKS)
    fun getBooks(): Call<GetBooksResponse>

    @GET(GET_BOOK_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>


}