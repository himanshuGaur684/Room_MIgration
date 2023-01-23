package com.example.roommigration.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.roommigration.database.model.User

@Database(
    entities = [User::class], version = 4, exportSchema = true, autoMigrations = [
        AutoMigration(
            from = 1, to = 2,
        ),
        AutoMigration(
            from = 2, to = 3, spec = MainDatabase.RenameAgeToCurrentAge::class
        ),
        AutoMigration(
            from = 3, to = 4, spec = MainDatabase.DeleteAgeColumn::class
        )
    ]
)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, "db").build()
        }
    }

    abstract fun getMainDao(): MainDao

    @RenameColumn(tableName = "User", fromColumnName = "age", toColumnName = "current_age")
    class RenameAgeToCurrentAge:AutoMigrationSpec

    @DeleteColumn(tableName = "User", columnName = "current_age")
    class DeleteAgeColumn : AutoMigrationSpec

}