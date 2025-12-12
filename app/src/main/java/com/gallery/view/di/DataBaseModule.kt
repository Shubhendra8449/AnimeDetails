package com.gallery.view.di

import android.content.Context
import androidx.room.Room
import com.gallery.view.data.local.dao.SeekhoDao
import com.gallery.view.data.local.db.SeekhoDatabase
import com.gallery.view.utils.DBConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideShopDataBase(@ApplicationContext context: Context): SeekhoDatabase {
        return Room.databaseBuilder(context.applicationContext,
            SeekhoDatabase::class.java, DBConstants.SEEKHO_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideShopDao(db: SeekhoDatabase): SeekhoDao =db.seekhoDao()

}
