package com.gallery.view.data.remote.utils

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HandleResponse @Inject constructor() {

        fun <T, R> handleResponse(
            response: Response<T>,
            successMapper: (T) -> R?
        ): Result<R> where T : Any {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val data = successMapper(body)
                    if (data != null) {
                        Result.success(data)
                    } else {
                        Result.failure(Exception("No data available"))
                    }
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("API call failed: ${response.code()}"))
            }
        }

}
