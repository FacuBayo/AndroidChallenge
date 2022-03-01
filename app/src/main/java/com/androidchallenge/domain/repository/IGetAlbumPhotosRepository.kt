package com.androidchallenge.domain.repository

import com.androidchallenge.data.repository.network.response.PhotoResponse
import com.highquality.base.data.Response
import kotlinx.coroutines.flow.Flow

interface IGetAlbumPhotosRepository {

    suspend fun getAlbumPhotos(albumId: String) : Flow<Response<List<PhotoResponse>>>
}