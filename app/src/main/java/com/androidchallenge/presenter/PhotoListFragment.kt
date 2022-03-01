package com.androidchallenge.presenter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.androidchallenge.R
import com.androidchallenge.databinding.FragmentPhotoListBinding
import com.androidchallenge.presenter.viewmodel.JsonPlaceHolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private lateinit var binding: FragmentPhotoListBinding
    private val navArgs: PhotoListFragmentArgs by navArgs()
    private val viewModel: JsonPlaceHolderViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoListBinding.bind(view)

        viewModel.fetchAlbumPhotos(navArgs.albumId.toString())

        viewModel.photosLiveData.observe(this, {
            Log.d("LISTA", it.size.toString())
        })

    }

}