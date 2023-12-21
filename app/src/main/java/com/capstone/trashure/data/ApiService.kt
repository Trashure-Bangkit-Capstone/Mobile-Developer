package com.capstone.trashure.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("order/orders")
    fun getOrders(): Call<OrderResponse>
}
