package com.example.roommigration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommigration.database.model.User

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getMainDao():MainDao

}