package com.capstone.trashure.ui.menu.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.trashure.R
import com.capstone.trashure.data.local.CardItem
import com.capstone.trashure.data.response.OrderResponse
import com.capstone.trashure.data.local.toCardItemList
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class AdminHomeAdapter(private val orderResponse: OrderResponse?) :
    RecyclerView.Adapter<AdminHomeAdapter.ViewHolder>() {

    private val cardItemList: List<CardItem> = orderResponse?.toCardItemList() ?: emptyList()
    private val dateFormatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    private val dateFormatOutput = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val itemView = inflater.inflate(R.layout.card_item, parent, false)

        // Return a new holder instance
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val cardItem: CardItem = cardItemList[position]

        // Parse and format the date
        val formattedDate = try {
            val date = dateFormatInput.parse(cardItem.date)
            dateFormatOutput.format(date)
        } catch (e: ParseException) {
            cardItem.date // Use the original date if parsing fails
        }

        // Set item views based on your views and data model
        holder.dateTextView.text = formattedDate
        holder.usernameTextView.text = cardItem.username
        holder.locationTextView.text = cardItem.location

        // Set click listeners or other actions for buttons
        holder.btnTake.setOnClickListener {
            // Handle "Take" button click
        }

        holder.btnDone.setOnClickListener {
            // Handle "Done" button click
        }

        holder.btnCancel.setOnClickListener {
            // Handle "Cancel" button click
        }
    }

    override fun getItemCount(): Int {
        return cardItemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val usernameTextView: TextView = itemView.findViewById(R.id.textViewUsername)
        val locationTextView: TextView = itemView.findViewById(R.id.textViewLocation)
        val btnTake: Button = itemView.findViewById(R.id.btnTake)
        val btnDone: Button = itemView.findViewById(R.id.btnDone)
        val btnCancel: Button = itemView.findViewById(R.id.btnCancel)
    }
}
