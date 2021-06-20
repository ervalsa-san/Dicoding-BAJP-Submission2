package com.ervalsa.tmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ervalsa.tmdb.data.source.MovieRepository
import com.ervalsa.tmdb.di.Injection
import com.ervalsa.tmdb.ui.detail.DetailMoviesViewModel
import com.ervalsa.tmdb.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unkown ViewModel class: " + modelClass.name)
        }
    }
}