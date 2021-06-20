package com.ervalsa.tmdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.data.source.MovieRepository

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getListNowPlayingMovies(): LiveData<List<DataEntity>> = movieRepository.getNowPlayingMovies()

    fun getListTvShowAiringToday(): LiveData<List<DataEntity>> = movieRepository.getTvShowAiringToday()
}