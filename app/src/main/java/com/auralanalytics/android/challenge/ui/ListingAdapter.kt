package com.auralanalytics.android.challenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.auralanalytics.android.challenge.R
import com.auralanalytics.android.challenge.models.Thing

/**
 * Adapter for listing `Thing` objects (posts) using `PostViewHolder`. Since
 * the Reddit API allows for paging, this extends `PagingDataAdapter` instead
 * of the default `RecyclerView.Adapter`.
 */
class ListingAdapter(private val clickListener: (thing: Thing) -> Unit) :
    PagingDataAdapter<Thing, PostViewHolder>(diffCallback) {

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Thing>() {
            override fun areItemsTheSame(old: Thing, new: Thing) =
                old.id == new.id

            override fun areContentsTheSame(old: Thing, new: Thing) =
                old == new
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val listingView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_listing, parent, false)
        return PostViewHolder(listingView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = getItem(position)!!

        holder.bind(data, clickListener)
    }
}
