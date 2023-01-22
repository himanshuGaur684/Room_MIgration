package com.example.roommigration.repository

import com.example.roommigration.database.MainDao
import com.example.roommigration.database.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val userDao: MainDao) {

    suspend fun insertData(user:User){
        userDao.insert(user)
    }

    suspend fun getAllUser():Flow<List<User>> = userDao.getAllUserList()

}