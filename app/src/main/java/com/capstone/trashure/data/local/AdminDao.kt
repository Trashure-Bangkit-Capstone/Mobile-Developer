package com.capstone.trashure.data.local

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface AdminDao {
    @Query("SELECT * FROM Admin WHERE email = :email AND password = :password")
    fun getAdminByEmailAndPassword(email: String, password: String): Admin?

    @Insert
    suspend fun insertAdmin(admin: Admin)
}
