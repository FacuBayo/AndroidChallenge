package com.androidchallenge.data.repository.network.api

import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.data.repository.network.response.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderApi {

    @GET("albums")
    suspend fun getAlbums() : Response<List<AlbumResponse>>

    @GET("albums/{albumId}/photos")
    suspend fun getAlbumPhotos(@Path("albumId") albumId : String) : Response<List<PhotoResponse>>


}