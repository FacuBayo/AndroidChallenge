package com.androidchallenge.data.repository.network.api

import com.androidchallenge.data.repository.network.response.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("albums")
    suspend fun getAlbums() : Response<List<AlbumResponse>>


}