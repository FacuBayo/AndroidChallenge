package com.androidchallenge.data.repository.network.response

data class PhotoResponse(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)