package com.androidchallenge.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.androidchallenge.R
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.domain.model.Album

class AlbumAdapter(private val albumList: List<AlbumResponse>, private val  onClickListener:(AlbumResponse) -> Unit) :
    RecyclerView.Adapter<AlbumViewHolder>() {

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        context = parent.context

        val layoutInflater = LayoutInflater.from(parent.context)

        return AlbumViewHolder(layoutInflater.inflate(R.layout.item_album, parent, false))


    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = albumList[position]

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_one)
        holder.bind(item, onClickListener)
        holder.itemView.startAnimation(animation)

    }

    override fun getItemCount(): Int = albumList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }


}