package com.example.keeppasswordv2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users WHERE userName == :name")
    suspend fun deleteUser(name: String)

    @Query("SELECT * FROM users WHERE userName == :name")
    fun findUser(name: String): List<User>

    @Query("SELECT userName FROM users")
    fun getAllUsersNames(): LiveData<List<String>>

    @Query("SELECT * FROM users WHERE userName == :name")
    fun getPasswordByName(name: String): LiveData<List<User>>

}