package com.androidchallenge.module


import com.androidchallenge.data.repository.JsonPlaceHolderRepository
import com.androidchallenge.data.repository.network.api.JsonPlaceHolderApi
import com.androidchallenge.domain.GetAlbumPhotosUseCase
import com.androidchallenge.domain.GetAlbumUseCase
import com.androidchallenge.domain.repository.IGetAlbumListRepository
import com.androidchallenge.domain.repository.IGetAlbumPhotosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class JsonPlaceHolderModule {


    @Provides
    fun provideFanatizApi(retrofit: Retrofit) = retrofit.create(JsonPlaceHolderApi::class.java)

    @Provides
    fun provideGetAlbumUseCase(repository: IGetAlbumListRepository) = GetAlbumUseCase(repository)

    @Provides
    fun provideGetAlbumPhotoUseCase(repository: IGetAlbumPhotosRepository) =
        GetAlbumPhotosUseCase(repository)


}


@Module
@InstallIn(SingletonComponent::class)
abstract class JsonPlaceHolderViewModelData {


    @Binds
    abstract fun bindIGetAlbumListRepository(repo: JsonPlaceHolderRepository): IGetAlbumListRepository

    @Binds
    abstract fun bindIGetAlbumPhotosRepositoryy(repo: JsonPlaceHolderRepository): IGetAlbumPhotosRepository


}