package com.dicoding.picodiploma.mycamera

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.mycamera.data.api.TrashItem
import com.dicoding.picodiploma.mycamera.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemsBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the currentImageUri and selectedTrashItem from the intent
        currentImageUri = intent.getParcelableExtra("currentImageUri")
        val selectedTrashItem = intent.getParcelableExtra<TrashItem>("trashItem")

        showImage()
        showItemDetails(selectedTrashItem)

  /*      orderButton.setOnClickListener {
            val quantity = quantityEditText.text.toString().toIntOrNull() ?: 0
            placeOrder(selectedTrashItem?.id ?: 0, quantity)
        }*/
    }

    private fun showImage() {
        currentImageUri?.let {
            // Load the image into the ImageView using the URI
            binding.itemImageView.setImageURI(it)
        }
    }

    private fun showItemDetails(trashItem: TrashItem?) {
        trashItem?.let {
            // Display item details, e.g., item name
            binding.itemNameTextView.text = it.name
        }
    }
}