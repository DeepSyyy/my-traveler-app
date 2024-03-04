package com.example.mytravelerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListPlaceAdapter(private val listPlace: ArrayList<Place>) :
    RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPlace: ImageView = itemView.findViewById(R.id.img_item_place)
        val titlePlace: TextView = itemView.findViewById(R.id.tv_title_place)
        val ratingPlace: TextView = itemView.findViewById(R.id.tv_rating_place)
        val placeLocation: TextView = itemView.findViewById(R.id.tv_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_container_place, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPlace.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (place, image, location, rating) = listPlace[position]
        holder.titlePlace.text = place
        Glide.with(holder.imgPlace.context).load(image).into(holder.imgPlace)
        holder.placeLocation.text = location
        holder.ratingPlace.text = rating.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition]) }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


}