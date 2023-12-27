package com.capstone.trashure.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashure.R
import com.capstone.trashure.ui.menu.profile.ProfileFragment

class PersonalInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_information)

        // Tambahkan action bar
        supportActionBar?.apply {
            title = "Personal Information" // Atur judul action bar di sini
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#0EAB00")))
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol panah kembali
            // Tambahan konfigurasi lainnya
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Ketika tombol panah kembali ditekan
                // Panggil fungsi untuk menavigasi ke fragment tertentu
                navigateToYourFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToYourFragment() {
        val fragment = ProfileFragment() // Ganti dengan fragment yang ingin dituju
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_profile_container, fragment) // Ganti R.id.container dengan ID dari container fragment Anda
        transaction.addToBackStack(null) // Jika ingin menambahkan ke back stack, supaya dapat kembali
        transaction.commit()
    }
}