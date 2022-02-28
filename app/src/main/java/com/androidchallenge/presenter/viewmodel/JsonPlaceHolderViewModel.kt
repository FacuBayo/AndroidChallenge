package com.androidchallenge.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidchallenge.domain.GetAlbumUseCase
import com.androidchallenge.domain.model.Album
import com.highquality.base.data.Response
import com.highquality.base.exception.NoInternetException
import com.highquality.base.presenter.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class JsonPlaceHolderViewModel @Inject constructor(
    private val getAlbumUseCase: GetAlbumUseCase
): BaseViewModel() {


    private val mutableAlbum: MutableLiveData<List<Album>> = MutableLiveData()
    val albumLiveData: LiveData<List<Album>> = mutableAlbum


    fun fetchAlbums() {
        viewModelScope.launch {
            notifyShowLoading()
            executeSimpleUseCase(getAlbumUseCase).single().collect {
                notifyRemoveLoading()
                when(it){
                    is Response.Success<List<Album>> -> {
                        mutableAlbum.value = it.data!!
                    }

                    is Response.Failure<Exception> -> {
                        when (it.error) {
                            is NoInternetException -> {
                                mutableConnection.value = false
                            }

                            else -> {
                                notifyError(it.error)
                            }
                        }
                    }
                }
            }
        }
    }
}