package com.example.movieapp.di

import com.example.movieapp.core.util.AppConstants
import com.example.movieapp.data.datasource.RemoteDataSource
import com.example.movieapp.data.remote.api.ApiService
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // region coroutines

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    // endregion

    // region Repository

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource
    ): MovieRepository {

        return MovieRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }

    // endregion

    // region Network

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(logger).build()
    }

    // endregion
}