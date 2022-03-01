package com.androidchallenge.presenter

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androidchallenge.R
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.databinding.FragmentAlbumListBinding
import com.androidchallenge.presenter.adapter.AlbumAdapter
import com.androidchallenge.presenter.viewmodel.JsonPlaceHolderViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumListFragment : Fragment(R.layout.fragment_album_list) {

    private lateinit var binding: FragmentAlbumListBinding
    private val viewModel: JsonPlaceHolderViewModel by viewModels()
    private lateinit var adapter: AlbumAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumListBinding.bind(view)
        setHasOptionsMenu(true)
        adapter = AlbumAdapter(mutableListOf()) { albumResponse -> onItemSelected(albumResponse) }

        setupObserversViewModel()
        viewModel.fetchAlbums()
        bindData()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)

        val item = menu.findItem(R.id.search_action)
        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.let {
                    it.filter.filter(newText)
                }
                return true
            }

        })

        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                adapter?.filter?.filter("")
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }


        })


        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun initRecycler(albumList: List<AlbumResponse>) {
        adapter = AlbumAdapter(albumList) { albumResponse -> onItemSelected(albumResponse) }
        binding.recyclerview.adapter = adapter
    }

    fun setupObserversViewModel() {
        viewModel.mutableLoading.observe(this, Observer {
            Log.d("mylogs", "loading status: ${it}")
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
            Log.d("mylogs", "connection: ${it}")
        })
        viewModel.mutableThrowables.observe(this, Observer {
            showGenericError(it != null)
            Log.d("mylogs", "generic error: ${it}")
        })
        viewModel.albumLiveData.observe(this, {
            initRecycler(it)
            Log.d("mylogs", "livedata: ${it}")
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

    private fun bindData() {
        activity?.title = ""
        binding.layoutGenericError.textviewTry.setOnClickListener {
            viewModel.fetchAlbums()
        }
        binding.layoutNoConnection.textviewTry.setOnClickListener {
            viewModel.fetchAlbums()
        }
    }

    private fun onItemSelected(albumResponse: AlbumResponse) {
        val action =
            AlbumListFragmentDirections.actionAlbumListFragmentToPhotoListFragment(albumResponse.id)
        findNavController().navigate(action)
    }


}