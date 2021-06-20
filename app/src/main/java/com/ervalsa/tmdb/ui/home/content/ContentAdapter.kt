package com.ervalsa.tmdb.ui.home.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.databinding.ItemsRowBinding
import com.ervalsa.tmdb.utils.Helper.IMAGE_API_ENDPOINT
import com.ervalsa.tmdb.utils.Helper.POSTER_SIZE_W185_ENDPOINT
import com.ervalsa.tmdb.utils.Helper.setImageWithGlide

class ContentAdapter(private val callback: ContentCallback) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private val listData = ArrayList<DataEntity>()

    fun setData(data: List<DataEntity>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsRowBinding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsRowBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ContentViewHolder(private val binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataEntity) {
            with(itemView) {
                data.poster?.let {
                    setImageWithGlide(context, IMAGE_API_ENDPOINT + POSTER_SIZE_W185_ENDPOINT + it, binding.imgPoster)
                }
                binding.tvTitle.text = data.title
                binding.itemCard.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }
        }
    }
}