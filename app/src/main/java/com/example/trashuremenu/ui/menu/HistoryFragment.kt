package com.example.trashuremenu.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trashuremenu.R
import com.example.trashuremenu.data.response.HistoryItem
import com.example.trashuremenu.data.response.HistoryResponse
import com.example.trashuremenu.data.retrofit.ApiConfig
import com.example.trashuremenu.databinding.FragmentHistoryBinding
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
            val call: Call<HistoryResponse> = apiService.getHistoryItems()

            call.enqueue(object : Callback<HistoryResponse> {
                override fun onResponse(call: Call<HistoryResponse>, response: Response<HistoryResponse>) {
                    if (response.isSuccessful) {
                        val historyResponse: HistoryResponse? = response.body()
                        historyResponse?.let {
                            val historyItems: List<HistoryItem> = it.data
                            historyAdapter = HistoryAdapter(historyItems) // Pastikan untuk mengganti HistoryAdapter dengan nama adapter yang sesuai
                            recyclerView.adapter = historyAdapter
                        }
                    } else {
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
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