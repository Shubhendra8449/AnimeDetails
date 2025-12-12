package com.gallery.view.domain.repository

import com.gallery.view.domain.model.AnimeResponse
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    suspend fun fetchImages(): Flow<Result<AnimeResponse>>
}