package com.androidchallenge.presenter.viewmodel

import androidx.lifecycle.Observer
import com.androidchallenge.base.BaseUnitTest
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.domain.GetAlbumPhotosUseCase
import com.androidchallenge.domain.GetAlbumUseCase
import com.highquality.base.data.Response
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito


class JsonPlaceHolderViewModelTest : BaseUnitTest(){


    private lateinit var viewModel: JsonPlaceHolderViewModel

    @Mock
    private lateinit var getAlbumUseCase: GetAlbumUseCase

    @Mock
    private lateinit var getAlbumPhotosUseCase: GetAlbumPhotosUseCase

    @Mock
    private lateinit var mutableAlbumList: Observer<List<AlbumResponse>>

    @Mock
    private lateinit var albumLiveDataObserver: Observer<List<AlbumResponse>>


    @Mock
    private lateinit var mutableConnectionObserver: Observer<Boolean>

    @Mock
    private lateinit var mutableLoadingObserver: Observer<Boolean>

    @Mock
    private lateinit var mutableThrowablesObserver: Observer<Throwable?>

    @Before
    fun setUp() {
        super.setup()

        viewModel = Mockito.spy(
            JsonPlaceHolderViewModel(
                getAlbumUseCase = getAlbumUseCase,
                getAlbumPhotosUseCase = getAlbumPhotosUseCase
            )
        )

        viewModel.albumLiveData.observeForever(albumLiveDataObserver)

        viewModel.mutableConnection.observeForever(mutableConnectionObserver)
        viewModel.mutableLoading.observeForever(mutableLoadingObserver)
        viewModel.mutableThrowables.observeForever(mutableThrowablesObserver)
    }

    @After
    override fun tearDown() {
        super.tearDown()

        viewModel.albumLiveData.removeObserver(albumLiveDataObserver)


        viewModel.mutableConnection.removeObserver(mutableConnectionObserver)
        viewModel.mutableLoading.removeObserver(mutableLoadingObserver)
        viewModel.mutableThrowables.removeObserver(mutableThrowablesObserver)
    }

    @Test
    fun `test albumList return success event`() = testDispatcher.runBlockingTest {

        val expectedResult = listOf<AlbumResponse>()

        val expectedFlow = flow {
            emit(Response.Success(expectedResult))
        }

        BDDMockito.given(getAlbumUseCase.execute()).willReturn(expectedFlow)

        viewModel.fetchAlbums()


        Mockito.verify(getAlbumUseCase).execute()
        Mockito.verify(mutableLoadingObserver).onChanged(true)
        Mockito.verify(mutableConnectionObserver).onChanged(true)
        Mockito.verify(albumLiveDataObserver, Mockito.times(1)).onChanged(Mockito.isNotNull())
    }

}