package com.ervalsa.tmdb.ui.home.content

import com.ervalsa.tmdb.data.DataEntity

interface ContentCallback {
    fun onItemClicked(data: DataEntity)
}