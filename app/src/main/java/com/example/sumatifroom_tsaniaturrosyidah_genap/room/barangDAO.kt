package com.example.sumatifroom_tsaniaturrosyidah_genap.room

import androidx.room.*

@Dao
interface barangDAO {
    @Insert
    fun addtbbarang(brg: tbbarang)

    @Update
    fun updatetbbarang(brg: tbbarang)

    @Delete
    fun deletetbbarang(brg: tbbarang)

    @Query("SELECT * FROM tbbarang")
    fun muncul(): List<tbbarang>

    @Query("SELECT * FROM tbbarang WHERE id =:idBarang")
    fun munculkan(idBarang: Int): List<tbbarang>

    @Query("SELECT * FROM tbbarang ORDER BY id ASC")
    fun urut(): List<tbbarang>
}