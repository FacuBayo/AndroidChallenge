package com.androidchallenge.domain.repository

import com.androidchallenge.data.repository.network.response.AlbumResponse
import kotlinx.coroutines.flow.Flow
import com.highquality.base.data.Response

interface IGetAlbumListRepository {

    suspend fun getAlbumList(): Flow<Response<List<AlbumResponse>>>
}