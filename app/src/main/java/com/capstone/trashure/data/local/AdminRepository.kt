package com.capstone.trashure.data.local

// AdminRepository.kt
class AdminRepository(private val adminDao: AdminDao) {
    suspend fun insertAdmin(admin: Admin) {
        adminDao.insertAdmin(admin)
    }

    fun getAdminByEmailAndPassword(email: String, password: String): Admin? {
        return adminDao.getAdminByEmailAndPassword(email, password)
    }
}