package com.example.hw5_retrofit.data.source.remote

import com.example.hw5_retrofit.common.Constants.Endpoint.GET_BOOKS
import com.example.hw5_retrofit.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookService {

    @GET(GET_BOOKS)
    fun getBooks(): Call<GetBooksResponse>
}