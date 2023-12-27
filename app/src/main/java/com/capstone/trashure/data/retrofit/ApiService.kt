package com.capstone.trashure.data.retrofit

import com.capstone.trashure.data.response.OrderResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("order/orders")
    fun getOrders(): Call<OrderResponse>
}
