package com.example.sumatifroom_tsaniaturrosyidah_genap.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [tbbarang::class],
    version = 1
        )
abstract class codepelita: RoomDatabase() {
    abstract fun tokoDAO() :barangDAO

    companion object{
        @Volatile  private var instance: codepelita?=null
        private val LOCK = Any ()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?:buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, codepelita::class.java,
            "205413_db"
        ).build()
    }
}