package com.androidchallenge.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.androidchallenge.R
import com.androidchallenge.data.repository.network.response.AlbumResponse
import com.androidchallenge.domain.model.Album

class AlbumAdapter(
    private val albumList: List<AlbumResponse>,
    private val onClickListener: (AlbumResponse) -> Unit
) :
    RecyclerView.Adapter<AlbumViewHolder>(), Filterable {

    lateinit var context: Context

    var albumListFilter = ArrayList<AlbumResponse>()

    init {
        albumListFilter = albumList as ArrayList<AlbumResponse>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        context = parent.context

        val layoutInflater = LayoutInflater.from(parent.context)

        return AlbumViewHolder(layoutInflater.inflate(R.layout.item_album, parent, false))


    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = albumListFilter[position]

        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_one)
        holder.bind(item, onClickListener)
        holder.itemView.startAnimation(animation)

    }

    override fun getItemCount(): Int = albumListFilter.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    albumListFilter = albumList as ArrayList<AlbumResponse>
                } else {
                    val resultList = ArrayList<AlbumResponse>()
                    for (row in albumList) {
                        if (row.title.lowercase().contains(p0.toString().lowercase())) {
                            resultList.add(row)
                        }
                    }

                    albumListFilter = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = albumListFilter
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                albumListFilter = results?.values as ArrayList<AlbumResponse>
                notifyDataSetChanged()
            }

        }
    }


}