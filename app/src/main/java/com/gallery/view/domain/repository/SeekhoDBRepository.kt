package com.gallery.view.domain.repository

import com.gallery.view.data.local.entity.SeekhoEntity

interface SeekhoDBRepository {
    suspend fun fetchDBData():  List<SeekhoEntity>
    suspend fun saveData(data:List<SeekhoEntity>)
}