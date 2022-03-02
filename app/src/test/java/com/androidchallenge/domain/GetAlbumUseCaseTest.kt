package com.androidchallenge.domain

import com.androidchallenge.base.BaseUnitTest
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.domain.repository.IGetAlbumListRepository
import com.highquality.base.data.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class GetAlbumUseCaseTest : BaseUnitTest(){

    @Mock
    private lateinit var repository: IGetAlbumListRepository

    private lateinit var useCase: GetAlbumUseCase

    @Before
    fun setUp() {
        useCase = Mockito.spy(GetAlbumUseCase(repository))
    }


    @Test
    fun `test execute success`() = testDispatcher.runBlockingTest {

        val mockResponse = listOf<AlbumResponse>()

        val flowExpected = flow {
            emit(Response.Success(mockResponse))
        }

        Mockito.`when`(
            repository.getAlbumList()
        ).thenReturn(flowExpected)



        useCase.execute().collect {
            Assert.assertNotNull(it)
            Assert.assertTrue(it is Response<List<AlbumResponse>>)
        }

        Mockito.verify(repository).getAlbumList()
    }
}