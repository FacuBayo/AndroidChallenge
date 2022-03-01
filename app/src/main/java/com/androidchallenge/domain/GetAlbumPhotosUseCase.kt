package com.androidchallenge.domain


import com.androidchallenge.data.repository.network.response.PhotoResponse
import com.androidchallenge.domain.repository.IGetAlbumPhotosRepository
import com.highquality.base.data.Response
import com.highquality.base.domain.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(private val repository: IGetAlbumPhotosRepository) :
    BaseUseCase<Flow<Response<List<PhotoResponse>>>>() {

    private var albumId = ""

     fun bind(albumId: String) {
        this.albumId = albumId
    }


    override suspend fun execute(): Flow<Response<List<PhotoResponse>>> {
        return repository.getAlbumPhotos(this.albumId)
    }

}