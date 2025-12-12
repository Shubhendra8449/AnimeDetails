package com.gallery.view.data.repository

import com.gallery.view.data.local.dao.SeekhoDao
import com.gallery.view.data.local.entity.SeekhoEntity
import com.gallery.view.domain.repository.SeekhoDBRepository
import javax.inject.Inject

class SeekhoDBRepositoryImpl @Inject constructor(
    private val db: SeekhoDao
) : SeekhoDBRepository {

    override suspend fun fetchDBData():  List<SeekhoEntity> {
        return db.getAllData()
    }

    override suspend fun saveData(data: List<SeekhoEntity>) {
       db.insertAllAnime(data)
    }

}
