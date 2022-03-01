package com.androidchallenge.data.repository.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class AlbumResponse(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = ""
) : Parcelable
