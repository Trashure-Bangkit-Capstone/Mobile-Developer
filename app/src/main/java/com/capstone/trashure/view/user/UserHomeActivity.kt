package com.capstone.trashure.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.trashure.R
import com.capstone.trashure.databinding.ActivityUserHomeBinding
import com.capstone.trashure.view.login.LoginActivity

class UserHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.cameraX.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }*/
    }
}