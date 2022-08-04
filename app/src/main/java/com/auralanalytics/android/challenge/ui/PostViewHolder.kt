package com.auralanalytics.android.challenge.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.auralanalytics.android.challenge.R
import com.auralanalytics.android.challenge.models.Thing
import com.google.android.material.card.MaterialCardView

/**
 * ViewHolder for `Thing` (reddit post) data. This only sets a few fields from
 * the object.
 */
class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val cardView: MaterialCardView = itemView.findViewById(R.id.cardView)
    private val thingTitle: TextView = itemView.findViewById(R.id.thingTitle)
    private val thingImage: ImageView = itemView.findViewById(R.id.thingImage)
    private val thingTime: TextView = itemView.findViewById(R.id.thingPostedTime)

    fun bind(item: Thing, clickListener: (thing: Thing) -> Unit) {
        cardView.setOnClickListener { clickListener(item) }

        thingTitle.text = item.title
        thingTime.text = itemView.context.getString(R.string.posted_by, item.author)

    }
}
