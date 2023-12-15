package com.capstone.trashure.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.trashure.data.local.User
import com.capstone.trashure.data.local.UserRepository
import kotlinx.coroutines.launch

// UserViewModel.kt

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _userLoggedIn = MutableLiveData<Boolean>()
    val userLoggedIn: LiveData<Boolean> get() = _userLoggedIn

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmailAndPassword(email, password)
            _userLoggedIn.value = user != null
        }
    }

    fun registerUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}