package com.androidchallenge.data.repository

import com.androidchallenge.data.repository.network.api.JsonPlaceHolderApi
import com.androidchallenge.domain.model.Album
import com.androidchallenge.domain.model.mapper.AlbumListResponseMapper
import com.androidchallenge.domain.repository.IGetAlbumListRepository
import com.highquality.base.data.Response
import com.highquality.base.exception.GenericException
import com.highquality.base.exception.ServiceErrorException
import com.highquality.base.exception.UnAuthorizeException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JsonPlaceHolderRepository @Inject constructor(private val jsonPlaceHolderApi: JsonPlaceHolderApi) :
    IGetAlbumListRepository {

    override suspend fun getAlbumList(): Flow<Response<List<Album>>> = flow {

        val response = jsonPlaceHolderApi.getSportEvents()

        when (response.code()) {
            200 -> {
                val mapped = AlbumListResponseMapper.toAlbumList(response.body()!!)

                emit(Response.Success(mapped))
            }

            401 -> {
                emit(
                    Response.Failure(
                        UnAuthorizeException(
                            statusCode = 401,
                            statusMessage = "Credentials Error"
                        )
                    )
                )
            }

            404 -> {
                emit(
                    Response.Failure(
                        ServiceErrorException(
                            statusCode = 401,
                            statusMessage = "Unknow endpoint"
                        )
                    )
                )
            }

            else -> {
                emit(
                    Response.Failure(
                        GenericException(
                            statusCode = 999,
                            statusMessage = "Something unexpected happened"
                        )
                    )
                )
            }
        }
    }.catch {
        emit(
            Response.Failure(
                GenericException(
                    statusCode = 999,
                    statusMessage = "Something unexpected happened"
                )
            )
        )
    }
}