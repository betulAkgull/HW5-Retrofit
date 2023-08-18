package com.example.hw5_retrofit.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavBook::class], version = 1)
abstract class FavBookDb : RoomDatabase() {

    abstract fun favBookDao(): FavBookDao

    companion object {

        private var instance: FavBookDb? = null

        fun getFavBookDb(context: Context): FavBookDb? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FavBookDb::class.java,
                    "favbook.db"
                ).allowMainThreadQueries().build()
            }

            return instance
        }


    }

}