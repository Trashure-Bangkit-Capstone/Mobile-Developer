package com.capstone.trashure.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashure.R


class KebijakanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kebijakan)

        // Tambahkan action bar
        supportActionBar?.apply {
            title = "Kebijakan Privasi" // Atur judul action bar di sini
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#0EAB00")))
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol panah kembali
            // Tambahan konfigurasi lainnya
        }
    }
}