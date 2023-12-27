package com.capstone.trashure.ui.menu.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashure.R
import com.capstone.trashure.data.response.DataItem

class HistoryAdapter(private val historyList: List<DataItem?>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_single_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = historyList[position]

        if (currentItem != null) {
            holder.dateTextView.text = currentItem.createdAt
        }
        // Tambahkan binding untuk atribut lainnya di sini
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.date_tv)
        // Tambahkan inisialisasi TextView untuk atribut lainnya di sini
    }
}