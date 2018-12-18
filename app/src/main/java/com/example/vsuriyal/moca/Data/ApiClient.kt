package com.example.vsuriyal.moca.Data

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object ApiClient {

    val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var retrofit: Retrofit? = null


    val client: Retrofit
        get() {
            val retro = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit ?:retro
        }
}