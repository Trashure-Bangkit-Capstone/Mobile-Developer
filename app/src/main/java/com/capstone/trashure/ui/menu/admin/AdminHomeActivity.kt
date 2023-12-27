package com.capstone.trashure.ui.menu.admin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashure.MainActivity
import com.capstone.trashure.data.retrofit.ApiService
import com.capstone.trashure.data.response.OrderResponse
import com.capstone.trashure.databinding.ActivityAdminHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var orderResponse: OrderResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

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
                    val recyclerView: RecyclerView = binding.recyclerView
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
        binding.logoutFab.setOnClickListener {
            // Logout the user
            logoutUser()
        }
    }

    private fun logoutUser() {
        // Clear login status from SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.apply()

        // Navigate back to the login activity
        val intent = Intent(this@AdminHomeActivity, MainActivity::class.java)
        startActivity(intent)
        finish() // finish current activity to prevent going back to user home
    }
}