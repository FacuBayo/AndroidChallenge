package com.androidchallenge.data.repository

import com.androidchallenge.base.BaseUnitTest
import com.androidchallenge.data.repository.network.api.JsonPlaceHolderApi
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.highquality.base.data.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock


class JsonPlaceHolderRepositoryTest : BaseUnitTest(){


    @Mock
    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    private lateinit var repository: JsonPlaceHolderRepository


    @Before
    fun setUp() {
        repository = JsonPlaceHolderRepository(jsonPlaceHolderApi)
    }


    @Test
    fun `test get albumList then success`(): Unit = runBlocking {

        val mockReturn = listOf<AlbumResponse>()

        val mockResponse = retrofit2.Response.success(mockReturn)

        BDDMockito.given(
            jsonPlaceHolderApi.getAlbums()
        ).willReturn(mockResponse)

        repository.getAlbumList().collect {
            when(it){
                is Response.Success -> {
                    Assert.assertNotNull(it.data)
                }
            }
        }
    }


}