package com.ervalsa.tmdb.di

import com.ervalsa.tmdb.data.source.MovieRepository
import com.ervalsa.tmdb.data.source.remote.RemoteDataSource

object Injection {

    fun provideMovieRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}