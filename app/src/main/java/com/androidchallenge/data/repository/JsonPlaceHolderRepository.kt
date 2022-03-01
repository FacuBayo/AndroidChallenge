package com.androidchallenge.data.repository

import com.androidchallenge.data.repository.network.api.JsonPlaceHolderApi
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.data.repository.network.response.PhotoResponse
import com.androidchallenge.domain.repository.IGetAlbumListRepository
import com.androidchallenge.domain.repository.IGetAlbumPhotosRepository
import com.highquality.base.data.Response
import com.highquality.base.exception.GenericException
import com.highquality.base.exception.NoInternetException
import com.highquality.base.exception.ServiceErrorException
import com.highquality.base.exception.UnAuthorizeException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class JsonPlaceHolderRepository @Inject constructor(private val jsonPlaceHolderApi: JsonPlaceHolderApi) :
    IGetAlbumListRepository, IGetAlbumPhotosRepository {

    override suspend fun getAlbumList(): Flow<Response<List<AlbumResponse>>> = flow {

        val response = jsonPlaceHolderApi.getAlbums()

        when (response.code()) {
            200 -> {

                emit(Response.Success(response.body()!!))
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
        it.printStackTrace()
        if (it is UnknownHostException) {
            emit(
                Response.Failure(
                    NoInternetException(
                        statusCode = 999,
                        statusMessage = "Something unexpected happened"
                    )
                )
            )
        } else {
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

    override suspend fun getAlbumPhotos(albumId: String): Flow<Response<List<PhotoResponse>>> =
        flow {

            val response = jsonPlaceHolderApi.getAlbumPhotos(albumId)

            when (response.code()) {
                200 -> {

                    emit(Response.Success(response.body()!!))
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
            it.printStackTrace()
            if (it is UnknownHostException) {
                emit(
                    Response.Failure(
                        NoInternetException(
                            statusCode = 999,
                            statusMessage = "Something unexpected happened"
                        )
                    )
                )
            } else {
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
}