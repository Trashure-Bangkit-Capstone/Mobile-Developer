package com.capstone.trashure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.trashure.databinding.ActivityMainBinding
import com.capstone.trashure.view.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStarted.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}