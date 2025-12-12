package com.gallery.view.data.remote.api

import com.gallery.view.domain.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("anime")
    suspend fun getImagesList(): retrofit2.Response<AnimeResponse>
}
