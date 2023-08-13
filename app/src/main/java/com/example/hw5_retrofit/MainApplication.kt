package com.example.hw5_retrofit

import android.app.Application
import com.example.hw5_retrofit.common.Constants.BASE_URL
import com.example.hw5_retrofit.data.source.remote.BookService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainApplication : Application() {

    companion object {
        var bookService: BookService? = null

        private const val TIMEOUT = 60L


        fun provideRetrofit() {
            val okHttpClient = OkHttpClient.Builder().apply {
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }.build()

            bookService = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
                client(okHttpClient)
                build()
            }.build().create(BookService::class.java)
        }

    }
}