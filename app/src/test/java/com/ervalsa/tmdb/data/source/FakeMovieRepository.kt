package com.ervalsa.tmdb.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.data.source.remote.RemoteDataSource
import com.ervalsa.tmdb.data.source.remote.response.MovieResponse
import com.ervalsa.tmdb.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getNowPlayingMovies(): LiveData<List<DataEntity>> {
        val listMovieResult = MutableLiveData<List<DataEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getNowPlayingMovies(object : RemoteDataSource.LoadNowPlayingMoviesCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<DataEntity>()
                    for (response in movieResponse) {
                        val movie = DataEntity(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DataEntity> {
        val movieResult = MutableLiveData<DataEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = DataEntity(
                        movieResponse.id,
                        movieResponse.name,
                        movieResponse.desc,
                        movieResponse.poster,
                        movieResponse.img_preview
                    )
                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvShowAiringToday(): LiveData<List<DataEntity>> {
        val listTvShowResult = MutableLiveData<List<DataEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowAiringToday(object : RemoteDataSource.LoadTvShowAiringTodayCallback {
                override fun onAllTvShowsReceived(tvShowResponse:List<TvShowResponse>) {
                    val tvShowList = ArrayList<DataEntity>()
                    for (response in tvShowResponse) {
                        val tvShow = DataEntity(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        tvShowList.add(tvShow)
                    }
                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DataEntity> {
        val tvShowResult = MutableLiveData<DataEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback {
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = DataEntity(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.desc,
                        tvShowResponse.poster,
                        tvShowResponse.img_preview
                    )
                    tvShowResult.postValue(tvShow)
                }
            })
        }
        return tvShowResult
    }
}