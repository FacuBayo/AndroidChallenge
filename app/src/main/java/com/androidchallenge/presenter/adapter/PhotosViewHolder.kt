package com.androidchallenge.presenter.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.androidchallenge.data.repository.network.response.PhotoResponse
import com.androidchallenge.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso


class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPhotoBinding.bind(view)

    fun bind(photoResponse: PhotoResponse){

        binding.textViewPhotoId.text = photoResponse.id.toString()
        binding.textViewPhotoTitle.text = photoResponse.title
        Picasso.get().load(photoResponse.url).into(binding.imageviewPhotoImg)

    }

}