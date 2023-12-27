package com.capstone.trashure.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.capstone.trashure.R
import com.capstone.trashure.databinding.ActivityBottomNavBinding
import com.capstone.trashure.ui.menu.history.HistoryFragment
import com.capstone.trashure.ui.menu.home.HomeFragment
import com.capstone.trashure.ui.menu.profile.ProfileFragment
import com.capstone.trashure.ui.menu.scan.ScanFragment

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_item -> replaceFragment(HomeFragment())
                R.id.scan_item -> replaceFragment(ScanFragment())
                R.id.history_item -> replaceFragment(HistoryFragment())
                R.id.profile_item -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true
        }

        supportActionBar?.hide()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}