package com.example.roommigration.di

import android.content.Context
import com.example.roommigration.database.MainDao
import com.example.roommigration.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideMainDatabase(@ApplicationContext context:Context):MainDatabase{
        return MainDatabase.getInstance(context)
    }

    @Provides
    fun provideMainDao(mainDatabase: MainDatabase):MainDao{
        return mainDatabase.getMainDao()
    }


}