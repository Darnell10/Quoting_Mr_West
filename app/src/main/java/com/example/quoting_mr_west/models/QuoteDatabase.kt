package com.example.quoting_mr_west.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Quote_Model::class), version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var instance: QuoteDatabase? = null
        private var Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuoteDatabase::class.java,
            "quotedatabase"
        ).build()
    }

}