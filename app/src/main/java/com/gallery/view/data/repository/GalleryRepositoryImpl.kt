package com.gallery.view.data.repository

import com.gallery.view.data.remote.api.ApiInterface
import com.gallery.view.data.remote.utils.HandleResponse
import com.gallery.view.domain.model.AnimeResponse
import com.gallery.view.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val api: ApiInterface,
    private val handler: HandleResponse
) : GalleryRepository {

    override suspend fun fetchImages(): Flow<Result<AnimeResponse>> = flow {
        try {
            val response = api.getImagesList()
            val result = handler.handleResponse(response) { it }
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

}
