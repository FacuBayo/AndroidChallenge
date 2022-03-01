package com.androidchallenge.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androidchallenge.R
import com.androidchallenge.databinding.FragmentPhotoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private lateinit var binding: FragmentPhotoListBinding
    private val navArgs: PhotoListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoListBinding.bind(view)

    }

}