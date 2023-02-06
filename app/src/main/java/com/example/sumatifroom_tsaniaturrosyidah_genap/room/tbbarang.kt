package com.example.sumatifroom_tsaniaturrosyidah_genap.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbbarang (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nama : String,
    val harga : Int,
    val quantity : Int
        )