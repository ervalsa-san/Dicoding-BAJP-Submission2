package com.ervalsa.tmdb.data.source.remote

import com.ervalsa.tmdb.data.source.remote.api.ApiClient
import com.ervalsa.tmdb.data.source.remote.response.MovieResponse
import com.ervalsa.tmdb.data.source.remote.response.TvShowResponse
import com.ervalsa.tmdb.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getNowPlayingMovies(callback: LoadNowPlayingMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getNowPlayingMovies().await().result?.let {  listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovies(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowAiringToday(callback: LoadTvShowAiringTodayCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvAiringToday().await().result?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadNowPlayingMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowAiringTodayCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShowResponse)
    }
}