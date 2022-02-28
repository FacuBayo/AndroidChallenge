package com.androidchallenge.domain

import com.androidchallenge.domain.model.Album
import com.androidchallenge.domain.repository.IGetAlbumListRepository
import com.highquality.base.data.Response
import com.highquality.base.domain.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(private val repository: IGetAlbumListRepository) :
    BaseUseCase<Flow<Response<List<Album>>>>() {


    override suspend fun execute(): Flow<Response<List<Album>>> {
        return repository.getAlbumList()
    }
}