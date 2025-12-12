package com.gallery.view.di


import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.gallery.view.data.remote.api.ApiInterface
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

     private val baseUrl = "https://api.jikan.moe/v4/top/"

    @Provides
    @Singleton
    fun provideApiClient(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(providesOkHttpClient())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    /**
     * Method to create [OkHttpClient] builder by adding required headers in the [Request]
     *
     * @return OkHttpClient object
     */
    @Singleton
    @Provides
    fun getHttpClient(): OkHttpClient.Builder {

        return OkHttpClient.Builder().addInterceptor {
            val original: Request = it.request()
            val requestBuilder: Request.Builder = original.newBuilder()

            val request = requestBuilder.build()
            val response: Response = it.proceed(request)
            return@addInterceptor response
        }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    }


    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()

}