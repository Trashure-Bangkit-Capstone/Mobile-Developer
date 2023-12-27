package com.capstone.trashure.ui.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashure.databinding.ActivitySignupBinding
import com.capstone.trashure.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val editTextUsername = binding.editTextUsername
        val editTextEmailRegister = binding.editTextEmailRegister
        val editTextPasswordRegister= binding.editTextPasswordRegister
        val editTextConfirmPassword= binding.editTextConfirmPassword
        val textViewLogin = binding.textViewLogIn
        val spinnerUserRole = binding.spinnerUserRole
        val buttonRegister = binding.buttonRegister

        // Set up TextWatcher for email
        editTextEmailRegister.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        })

        // Set up TextWatcher for password
        editTextPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        })

        // Set up TextWatcher for confirm password
        editTextConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateFields()
            }
        })

        // Set up the spinner
        val roles = arrayOf("User", "Admin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)
        spinnerUserRole.adapter = adapter

        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString()
            val email = editTextEmailRegister.text.toString()
            val password = editTextPasswordRegister.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()
            val selectedRole = spinnerUserRole.selectedItem.toString()

            // Check if entered credentials are valid
            if (isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password, confirmPassword)) {
                // Registration successful, save data to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.putString("role", selectedRole)
                editor.apply()

                // You can add logic here to differentiate between admin and user
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                // Invalid credentials, display an error message
                Toast.makeText(this@SignUpActivity, "Invalid credentials. Please check your input.", Toast.LENGTH_SHORT).show()
            }
        }
        textViewLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateFields() {
        val editTextEmailRegister = binding.editTextEmailRegister
        val editTextPasswordRegister= binding.editTextPasswordRegister
        val editTextConfirmPassword= binding.editTextConfirmPassword

        val email = editTextEmailRegister.text.toString()
        val password = editTextPasswordRegister.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()

        // Validate email format
        if (!isValidEmail(email)) {
            editTextEmailRegister.error = "Invalid email format"
        } else {
            editTextEmailRegister.error = null
        }

        // Validate password length
        if (password.length < 8) {
            editTextPasswordRegister.error = "Password must be at least 8 characters"
        } else {
            editTextPasswordRegister.error = null
        }

        // Validate confirm password
        if (!isValidConfirmPassword(password, confirmPassword)) {
            editTextConfirmPassword.error = "Passwords do not match"
        } else {
            editTextConfirmPassword.error = null
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    private fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
