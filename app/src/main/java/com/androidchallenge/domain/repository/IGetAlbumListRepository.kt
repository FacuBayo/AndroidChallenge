package com.androidchallenge.domain.repository

import com.androidchallenge.domain.model.Album
import kotlinx.coroutines.flow.Flow
import com.highquality.base.data.Response

interface IGetAlbumListRepository {

    suspend fun getAlbumList(): Flow<Response<List<Album>>>
}