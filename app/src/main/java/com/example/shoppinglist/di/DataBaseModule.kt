package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglist.data.db.dao.Dao
import com.example.shoppinglist.data.db.dao.ShoppingDao
import com.example.shoppinglist.data.db.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext appContext: Context) : AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "DATABASE"
        ).build()
    }

    @Provides
    fun provideDao(appDataBase: AppDataBase): Dao {
        return appDataBase.dao()
    }
    @Provides
    fun provideShoppingDao(appDataBase: AppDataBase): ShoppingDao{
        return appDataBase.shoppingDao()
    }
}