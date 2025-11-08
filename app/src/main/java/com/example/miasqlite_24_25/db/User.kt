package com.example.miasqlite_24_25.db

import android.os.Parcelable
import androidx.room.Entity

import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val number: String
): Parcelable