package com.ervalsa.tmdb.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.data.source.MovieRepository
import com.ervalsa.tmdb.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<DataEntity>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(movieRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val movie = MutableLiveData<List<DataEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(movieRepository.getNowPlayingMovies()).thenReturn(movie)

        val dataListMovie = viewModel.getListNowPlayingMovies().value

        verify(movieRepository).getNowPlayingMovies()
        assertNotNull(dataListMovie)
        assertEquals(10, dataListMovie?.size)

        viewModel.getListNowPlayingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getListTvShowAiringToday() {
        val tvShow = MutableLiveData<List<DataEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(movieRepository.getTvShowAiringToday()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getListTvShowAiringToday().value

        verify(movieRepository).getTvShowAiringToday()
        assertNotNull(dataListTvShow)
        assertEquals(10, dataListTvShow?.size)

        viewModel.getListTvShowAiringToday().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}