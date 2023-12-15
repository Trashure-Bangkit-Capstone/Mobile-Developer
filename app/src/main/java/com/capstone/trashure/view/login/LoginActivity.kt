package com.capstone.trashure.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.trashure.R
import com.capstone.trashure.view.UserViewModel

// LoginActivity.kt
class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            viewModel.loginUser(email, password)
        }

        viewModel.userLoggedIn.observe(this, { isLoggedIn ->
            if (isLoggedIn) {
                // Handle user login success
                Toast.makeText(this, "User login success", Toast.LENGTH_SHORT).show()
            } else {
                // Handle user login failure
                Toast.makeText(this, "User login failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}



/*    binding.tvSignUp.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }*/