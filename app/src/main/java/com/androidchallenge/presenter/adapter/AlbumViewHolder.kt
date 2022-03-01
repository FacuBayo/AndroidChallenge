package com.androidchallenge.presenter.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.databinding.ItemAlbumBinding
import com.androidchallenge.domain.model.Album

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAlbumBinding.bind(view)

    fun bind(album: AlbumResponse, onClickListener: (AlbumResponse) -> Unit) {

        binding.textViewAlbumId.text = "ID: ${album.id}"
        binding.textViewAlbumTitle.text = album.title

        itemView.setOnClickListener { onClickListener(album) }

    }
}