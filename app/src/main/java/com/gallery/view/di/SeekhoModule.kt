package com.gallery.view.di

import com.gallery.view.data.local.dao.SeekhoDao
import com.gallery.view.data.remote.api.ApiInterface
import com.gallery.view.data.remote.utils.HandleResponse
import com.gallery.view.data.repository.GalleryRepositoryImpl
import com.gallery.view.data.repository.SeekhoDBRepositoryImpl
import com.gallery.view.domain.repository.GalleryRepository
import com.gallery.view.domain.repository.SeekhoDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SeekhoModule {

    @Provides
    fun providesSeekhoRemoteRepository(
        backendApiService: ApiInterface,
        handler: HandleResponse
    ): GalleryRepository = GalleryRepositoryImpl(backendApiService,handler)


    @Provides
    fun providesSeekhoLocalRepository(
        dao: SeekhoDao,
    ): SeekhoDBRepository = SeekhoDBRepositoryImpl(dao)

}