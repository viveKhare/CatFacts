package com.sipl.catfacts.di

import com.sipl.catfacts.constant.Constants
import com.sipl.catfacts.data.remote.CatFactApi
import com.sipl.catfacts.data.repository.CatFactsRepositoryImpl
import com.sipl.catfacts.domain.respository.CatFactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Http2
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getCatFactsApi(): CatFactApi {
        val logging = HttpLoggingInterceptor()
// set your desired log level
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
// add your other interceptors …
// add logging as last interceptor
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging)
        val build = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()

        return build.create(CatFactApi::class.java)
    }

    @Provides
    @Singleton
    fun getCatFactRepoImpl(catFactApi: CatFactApi): CatFactsRepository {
        return CatFactsRepositoryImpl(catFactApi)
    }
}