package com.gallery.view.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gallery.view.utils.DBConstants

@Entity(tableName = DBConstants.SEEKHO_TABLE)
data class SeekhoEntity(
    @PrimaryKey
    val mal_id: Long,             // Keep MAL ID as the primary key
    val seekhoJsonData: String    // Store full JSON of anime
)

