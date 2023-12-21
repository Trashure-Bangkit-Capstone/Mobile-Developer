package com.capstone.trashure.view.login

// src/main/java/com/example/yourpackage/MainActivity.kt
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashure.R
import com.capstone.trashure.databinding.ActivityLoginBinding
import com.capstone.trashure.view.admin.AdminHomeActivity
import com.capstone.trashure.view.signup.SignUpActivity
import com.capstone.trashure.view.user.UserHomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editTextEmail = binding.editTextEmail
        val editTextPassword = binding.editTextPassword
        val buttonLogin = binding.buttonLogin
        val textViewRegister = binding.textViewRegister

        // Check if the user is already logged in
        if (isLoggedIn()) {
            // User is already logged in, navigate to appropriate activity based on role
            val storedRole = sharedPreferences.getString("role", "")
            if (storedRole == "Admin") {
                val intent = Intent(this@LoginActivity, AdminHomeActivity::class.java)
                startActivity(intent)
                finish() // finish current activity to prevent going back to login
            } else {
                val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
                startActivity(intent)
                finish() // finish current activity to prevent going back to login
            }
        }

        // Set up TextWatcher for email
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        })

        // Set up TextWatcher for password
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        })

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Check if entered credentials are valid
            if (isValidEmail(email) && isValidPassword(password)) {
                val storedEmail = sharedPreferences.getString("email", "")
                val storedPassword = sharedPreferences.getString("password", "")
                val storedRole = sharedPreferences.getString("role", "")

                // Check if entered credentials match stored credentials
                if (email == storedEmail && password == storedPassword) {
                    // Login successful, navigate to appropriate activity based on role
                    if (storedRole == "Admin") {
                        val intent = Intent(this@LoginActivity, AdminHomeActivity::class.java)
                        startActivity(intent)
                        saveLoginStatus() // save login status
                        finish() // finish current activity to prevent going back to login
                    } else {
                        val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
                        startActivity(intent)
                        saveLoginStatus() // save login status
                        finish() // finish current activity to prevent going back to login
                    }
                } else {
                    // Login failed
                    Toast.makeText(this@LoginActivity, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Invalid credentials, display an error message
                Toast.makeText(this@LoginActivity, "Invalid credentials. Please check your input.", Toast.LENGTH_SHORT).show()
            }
        }

        textViewRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveLoginStatus() {
        // Save login status to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun isLoggedIn(): Boolean {
        // Check if the user is already logged in
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
    private fun validateFields() {
        val editTextEmail = binding.editTextEmail
        val editTextPassword = binding.editTextPassword

        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        // Validate email format
        if (!isValidEmail(email)) {
            editTextEmail.error = "Invalid email format"
        } else {
            editTextEmail.error = null
        }

        // Validate password length
        if (password.length < 8) {
            editTextPassword.error = "Password must be at least 8 characters"
        } else {
            editTextPassword.error = null
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}