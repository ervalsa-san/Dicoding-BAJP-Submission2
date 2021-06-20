package com.ervalsa.tmdb.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.databinding.ActivityDetailBinding
import com.ervalsa.tmdb.databinding.ContentDetailMovieBinding
import com.ervalsa.tmdb.utils.Helper.IMAGE_API_ENDPOINT
import com.ervalsa.tmdb.utils.Helper.POSTER_SIZE_W185_ENDPOINT
import com.ervalsa.tmdb.utils.Helper.POSTER_SIZE_W780_ENDPOINT
import com.ervalsa.tmdb.utils.Helper.TYPE_MOVIE
import com.ervalsa.tmdb.utils.Helper.TYPE_TVSHOW
import com.ervalsa.tmdb.utils.Helper.setImageWithGlide
import com.ervalsa.tmdb.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailContentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentDetailMovieBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getMovieDetail(id).observe(this, Observer {
                displayData(it)
            })
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            viewModel.getTvShowDetail(id).observe(this, Observer {
                it?.let {
                    displayData(it)
                }
            })
        }

    }

    private fun displayData(data: DataEntity) {
        detailContentDetailMovieBinding.tvTitle.text = data.title
        detailContentDetailMovieBinding.tvDescription.text = data.description
        setImageWithGlide(this, IMAGE_API_ENDPOINT + POSTER_SIZE_W185_ENDPOINT + data.poster, detailContentDetailMovieBinding.imgPoster)
        setImageWithGlide(this, IMAGE_API_ENDPOINT + POSTER_SIZE_W780_ENDPOINT + data.img_preview, detailContentDetailMovieBinding.imgPosterLandscape)
    }
}