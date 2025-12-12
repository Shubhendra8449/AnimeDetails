package com.gallery.view.domain.usecases

import com.gallery.view.data.local.entity.SeekhoEntity
import com.gallery.view.domain.model.AnimeData
import com.gallery.view.domain.repository.GalleryRepository
import com.gallery.view.domain.repository.SeekhoDBRepository
import com.gallery.view.utils.NetworkHelper
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val repo: GalleryRepository,
    private val dbRepo: SeekhoDBRepository,
    private val networkHelper: NetworkHelper
) {

    private val gson = Gson()

    operator fun invoke(isApiCall:Boolean): Flow<Result<List<AnimeData>>> = flow {

        emit(Result.success(emptyList()))

        if (networkHelper.isNetworkAvailable() && isApiCall) {

            repo.fetchImages().collect { result ->

                result.onSuccess { response ->
                    val list = response.data

                    // SAVE CLEAN JSON PER ROW
                    val entities = list.map { anime ->
                        SeekhoEntity(
                            mal_id = anime.mal_id.toLong(),
                            seekhoJsonData = gson.toJson(anime)
                        )
                    }

                    dbRepo.saveData(entities)

                    emit(Result.success(list))
                }

                result.onFailure { emit(Result.failure(it)) }
            }

        } else {

            val localData = dbRepo.fetchDBData()

            if (localData.isNotEmpty()) {

                val mappedList = localData.map { entity ->
                    gson.fromJson(entity.seekhoJsonData, AnimeData::class.java)
                }

                emit(Result.success(mappedList))

            } else {
                emit(Result.failure(Exception("No Internet & No Local Data")))
            }
        }
    }
}
