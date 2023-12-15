package com.capstone.trashure.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.trashure.data.local.Admin
import com.capstone.trashure.data.local.AdminRepository
import kotlinx.coroutines.launch

// AdminViewModel.kt
class AdminViewModel(private val repository: AdminRepository) : ViewModel() {
    private val _adminLoggedIn = MutableLiveData<Boolean>()
    val adminLoggedIn: LiveData<Boolean> get() = _adminLoggedIn

    fun loginAdmin(email: String, password: String) {
        viewModelScope.launch {
            val admin = repository.getAdminByEmailAndPassword(email, password)
            _adminLoggedIn.value = admin != null
        }
    }

    fun registerAdmin(admin: Admin) {
        viewModelScope.launch {
            repository.insertAdmin(admin)
        }
    }
}
