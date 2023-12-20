package com.example.trashuremenu.data.retrofit

import com.example.trashuremenu.data.response.HistoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("order/orders")
    fun getHistoryItems(): Call<HistoryResponse>
}