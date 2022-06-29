package com.sipl.catfacts.data.remote

import com.sipl.catfacts.data.remote.dto.CatFacts
import retrofit2.http.GET
import retrofit2.http.Query



interface CatFactApi {
    @GET("facts")
    suspend fun getCatFacts(@Query("page") page: String, @Query("limit") limit: String): CatFacts
}