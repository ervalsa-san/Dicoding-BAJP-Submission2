package com.ervalsa.tmdb.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ervalsa.tmdb.data.source.remote.RemoteDataSource
import com.ervalsa.tmdb.ui.LiveDataTestUtil
import com.ervalsa.tmdb.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val listMovieResponse = DataDummy.generateDataMovieDummyResponse()
    private val listTvShowResponse = DataDummy.generateDummyTvShowsResponse()
    private val movieResponse = DataDummy.generateDataMovieDummyResponse()[0]
    private val tvShowResponse = DataDummy.generateDummyTvShowsResponse()[0]
    private val movieId = listMovieResponse[0].id
    private val tvShowId = listTvShowResponse[0].id

    @Test
    fun getNowPlayingMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadNowPlayingMoviesCallback).onAllMoviesReceived(listMovieResponse)
                null
            }.`when`(remote).getNowPlayingMovies(any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getNowPlayingMovies())

        runBlocking {
            verify(remote).getNowPlayingMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(movieResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.id, data.Id)
    }

    @Test
    fun getTvShowOnTheAir() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowAiringTodayCallback).onAllTvShowsReceived(listTvShowResponse)
                null
            }.`when`(remote).getTvShowAiringToday(any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getTvShowAiringToday())

        runBlocking {
            verify(remote).getTvShowAiringToday(any())
        }

        assertNotNull(data)
        assertEquals(listTvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(tvShowResponse)
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val data = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.id, data.Id)
    }
}