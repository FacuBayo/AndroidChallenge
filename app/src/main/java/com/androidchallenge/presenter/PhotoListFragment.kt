package com.androidchallenge.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androidchallenge.R
import com.androidchallenge.data.repository.network.response.PhotoResponse
import com.androidchallenge.databinding.FragmentPhotoListBinding
import com.androidchallenge.presenter.adapter.AlbumAdapter
import com.androidchallenge.presenter.adapter.PhotosAdapter
import com.androidchallenge.presenter.viewmodel.JsonPlaceHolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private lateinit var binding: FragmentPhotoListBinding
    private val navArgs: PhotoListFragmentArgs by navArgs()
    private val viewModel: JsonPlaceHolderViewModel by viewModels()
    private lateinit var adapter: PhotosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoListBinding.bind(view)

        setupObserversViewModel()
        fetchAlbumPhotosList()
        bindData()
    }


    private fun bindData() {
        binding.layoutGenericError.textviewTry.setOnClickListener {
            findNavController().navigate(R.id.action_photoListFragment_to_albumListFragment)
        }
        binding.layoutNoConnection.textviewTry.setOnClickListener {
            findNavController().navigate(R.id.action_photoListFragment_to_albumListFragment)
        }
    }

    private fun fetchAlbumPhotosList() {
        viewModel.fetchAlbumPhotos(navArgs.albumId.toString())
    }

    private fun setupObserversViewModel() {
        viewModel.mutableLoading.observe(this, Observer {
            if (it) {
                binding.mainContainer.visibility = View.GONE
                binding.layoutLoading.root.visibility = View.VISIBLE
            } else {
                binding.mainContainer.visibility = View.VISIBLE
                binding.layoutLoading.root.visibility = View.GONE
            }
        })
        viewModel.mutableConnection.observe(this, Observer {
            showNoConnection(it)
        })
        viewModel.mutableThrowables.observe(this, Observer {
            showGenericError(it != null)
        })
        viewModel.photosLiveData.observe(this, {
            initRecycler(it)
        })
    }


    private fun showNoConnection(value: Boolean) {
        if (value) {
            binding.layoutNoConnection.container.visibility = View.GONE
            binding.mainContainer.visibility = View.VISIBLE
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
            binding.layoutNoConnection.container.visibility = View.VISIBLE
            binding.mainContainer.visibility = View.GONE
        }
    }

    private fun showGenericError(value: Boolean) {
        if (value) {
            binding.layoutGenericError.root.visibility = View.VISIBLE
            binding.mainContainer.visibility = View.GONE
        } else {
            binding.layoutGenericError.root.visibility = View.GONE
            binding.mainContainer.visibility = View.VISIBLE
        }
    }

    private fun initRecycler(photoList: List<PhotoResponse>) {
        adapter = PhotosAdapter(photoList)
        binding.recyclerviewPhotos.adapter = adapter
    }

}