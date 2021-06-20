package com.ervalsa.tmdb.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEntity (
    var Id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var poster: String? = null,
    var img_preview: String? = null
) : Parcelable