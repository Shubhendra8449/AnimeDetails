package com.gallery.view.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gallery.view.R
import com.gallery.view.databinding.ItemAnimeBinding
import com.gallery.view.domain.model.AnimeData

class AnimeAdapter(
    private val list: List<AnimeData>,
    private val onClick: (AnimeData) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeVH>() {

    inner class AnimeVH(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: AnimeData) {
            binding.tvTitle.text = item.title
            binding.tvEpisodes.text = "Episodes: ${item.episodes ?: "N/A"}"
            binding.tvRating.text = "Rating: ${item.score ?: "N/A"}"

            Glide.with(itemView)
                .load(item.images?.jpg?.image_url)
                .placeholder(R.drawable.placeholder)   // Show while loading
                .error(R.drawable.placeholder)
                .into(binding.imgPoster)

            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeVH {
        return AnimeVH(
            ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AnimeVH, position: Int) {
        val item = list[position]
        holder.bind(item)


    }
}
