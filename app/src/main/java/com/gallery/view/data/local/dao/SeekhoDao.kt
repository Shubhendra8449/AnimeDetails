package com.gallery.view.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gallery.view.data.local.entity.SeekhoEntity

@Dao
interface SeekhoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: SeekhoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(list: List<SeekhoEntity>)

    @Query("SELECT * FROM seekho_table")
    suspend fun getAllData(): List<SeekhoEntity>
}

