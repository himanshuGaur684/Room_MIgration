package com.example.roommigration.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommigration.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert
    suspend fun insert(user:User)

    @Query("SELECT * FROM User")
    fun getAllUserList(): Flow<List<User>>


}