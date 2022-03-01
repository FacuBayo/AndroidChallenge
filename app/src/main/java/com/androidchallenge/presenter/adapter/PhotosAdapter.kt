package com.androidchallenge.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidchallenge.R
import com.androidchallenge.data.repository.network.response.PhotoResponse

class PhotosAdapter(private val photoList: List<PhotoResponse>) :
    RecyclerView.Adapter<PhotosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PhotosViewHolder(layoutInflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = photoList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int = photoList.size


}