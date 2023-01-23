package com.example.roommigration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommigration.database.migration.MIGRATION_1_2
import com.example.roommigration.database.migration.MIGRATION_2_3
import com.example.roommigration.database.model.User

@Database(entities = [User::class], version = 3, exportSchema = true)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, "db")
                .addMigrations(
                    MIGRATION_1_2,MIGRATION_2_3
                )
                .build()
        }
    }

    abstract fun getMainDao():MainDao

}