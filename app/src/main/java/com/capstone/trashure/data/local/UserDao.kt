package com.capstone.trashure.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// UserDao.kt
@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): User?

    @Insert
    suspend fun insertUser(user: User)
}

