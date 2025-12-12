package com.gallery.view.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gallery.view.data.local.dao.SeekhoDao
import com.gallery.view.data.local.entity.SeekhoEntity

@Database(
    entities =  [SeekhoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SeekhoDatabase : RoomDatabase(){
    abstract fun seekhoDao(): SeekhoDao
}

