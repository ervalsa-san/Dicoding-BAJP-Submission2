package com.ervalsa.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.data.source.MovieRepository

class DetailMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<DataEntity> =
        movieRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<DataEntity> =
        movieRepository.getTvShowDetail(tvShowId)
}