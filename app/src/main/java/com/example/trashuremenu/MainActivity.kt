package com.example.trashuremenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.trashuremenu.databinding.ActivityMainBinding
import com.example.trashuremenu.ui.menu.HistoryFragment
import com.example.trashuremenu.ui.menu.HomeFragment
import com.example.trashuremenu.ui.menu.ProfileFragment
import com.example.trashuremenu.ui.menu.ScanFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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