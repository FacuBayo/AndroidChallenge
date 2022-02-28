package com.androidchallenge.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.androidchallenge.R
import com.androidchallenge.databinding.FragmentAlbumListBinding


class AlbumListFragment : Fragment(R.layout.fragment_album_list) {

    private lateinit var binding: FragmentAlbumListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAlbumListBinding.bind(view)
    }

}