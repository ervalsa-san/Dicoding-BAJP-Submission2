package com.ervalsa.tmdb.data.source

import androidx.lifecycle.LiveData
import com.ervalsa.tmdb.data.DataEntity

interface MovieDataSource {

    fun getNowPlayingMovies(): LiveData<List<DataEntity>>

    fun getMovieDetail(movieId: Int): LiveData<DataEntity>

    fun getTvShowAiringToday(): LiveData<List<DataEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<DataEntity>
}