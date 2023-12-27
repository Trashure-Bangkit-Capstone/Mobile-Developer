package com.capstone.trashure.ui.menu.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashure.R
import com.capstone.trashure.data.response.DataItem
import com.capstone.trashure.data.response.OrderResponse
import com.capstone.trashure.data.retrofit.ApiConfig
import com.capstone.trashure.databinding.FragmentHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.historyRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val apiService = ApiConfig.getApiService()
        if (apiService != null) {
            // Lakukan pemanggilan pada objek apiService karena tidak null
            val call: Call<OrderResponse> = apiService.getOrders()

            call.enqueue(object : Callback<OrderResponse> {
                override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.isSuccessful) {
                        val historyResponse: OrderResponse? = response.body()
                        historyResponse?.let {
                            val dataItems: List<DataItem?> = it.data
                            historyAdapter = HistoryAdapter(dataItems) // Pastikan untuk mengganti HistoryAdapter dengan nama adapter yang sesuai
                            recyclerView.adapter = historyAdapter
                        }
                    } else {
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error: " + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            // Tangani kasus jika apiService bernilai null
            // Misalnya, tampilkan pesan kesalahan atau lakukan tindakan lain yang sesuai
            Toast.makeText(requireContext(), "ApiService is null", Toast.LENGTH_SHORT).show()
        }



        // Dropdown
        val dropdown = resources.getStringArray(R.array.dropdown)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, dropdown)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}