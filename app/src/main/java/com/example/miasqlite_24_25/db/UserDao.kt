package com.example.miasqlite_24_25.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Update
    fun editUser(user: User)

    @Query("SELECT*FROM User")
    fun getAllUser(): List<User>
}