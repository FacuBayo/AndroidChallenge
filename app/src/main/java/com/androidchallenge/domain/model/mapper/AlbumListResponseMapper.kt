package com.androidchallenge.domain.model.mapper

import com.androidchallenge.data.repository.network.response.AlbumListResponse
import com.androidchallenge.domain.model.Album

object AlbumListResponseMapper {

    fun toAlbumList(response: AlbumListResponse) : List<Album>{

        return response.results.map {
            Album(
                userId = it.userId,
                id = it.id,
                title = it.title
            )
        }

    }
}