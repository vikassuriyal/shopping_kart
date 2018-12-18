package com.example.vsuriyal.moca.Interface

import com.example.vsuriyal.moca.Beans.BeanClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/photos")
    fun getItems(): Call<List<BeanClass.ItemListBean>>

}