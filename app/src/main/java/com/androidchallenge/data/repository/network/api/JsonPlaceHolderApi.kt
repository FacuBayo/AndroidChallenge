package com.androidchallenge.data.repository.network.api

import com.androidchallenge.data.repository.network.response.AlbumListResponse
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("albums")
    suspend fun getSportEvents() : Response<AlbumListResponse>


}