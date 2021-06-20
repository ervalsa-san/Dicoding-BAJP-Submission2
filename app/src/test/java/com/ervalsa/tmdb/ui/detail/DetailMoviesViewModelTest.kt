package com.ervalsa.tmdb.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.data.source.MovieRepository
import com.ervalsa.tmdb.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.Id
    private val tvShowId = dummyTvShow.Id

    private lateinit var viewModel: DetailMoviesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<DataEntity>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<DataEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(movieRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value as DataEntity

        assertNotNull(movieData)
        assertEquals(dummyMovie.Id, movieData.Id)
        assertEquals(dummyMovie.title, movieData.title)
        assertEquals(dummyMovie.description, movieData.description)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.img_preview, movieData.img_preview)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<DataEntity>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value as DataEntity

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.Id, tvShowData.Id)
        assertEquals(dummyTvShow.title, tvShowData.title)
        assertEquals(dummyTvShow.description, tvShowData.description)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.img_preview, tvShowData.img_preview)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

}