package com.capstone.trashure.view.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashure.R
import com.capstone.trashure.data.ApiService
import com.capstone.trashure.data.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminHomeActivity : AppCompatActivity() {

    private var orderResponse: OrderResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        // Buat instance Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://trashure-api-v4efcnkgoq-as.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Buat instance ApiService
        val apiService = retrofit.create(ApiService::class.java)

        // Lakukan request ke API
        val call = apiService.getOrders()
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful) {
                    // Tanggapan berhasil, dapatkan data dari response
                    orderResponse = response.body()

                    // Tampilkan data di RecyclerView atau tempat lain yang sesuai
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@AdminHomeActivity)
                    val adapter = AdminHomeAdapter(orderResponse)
                    recyclerView.adapter = adapter
                } else {
                    // Tanggapan tidak berhasil, tindakan sesuai kebutuhan
                    // ...
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                // Gagal melakukan request, tindakan sesuai kebutuhan
                // ...
            }
        })
    }
}