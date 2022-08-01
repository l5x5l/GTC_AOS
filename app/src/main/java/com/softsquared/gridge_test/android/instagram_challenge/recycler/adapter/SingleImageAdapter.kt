package com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewSingleImageBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder.SingleImageViewHolder

class SingleImageAdapter(private var imageUrlList : List<String> = listOf()) : RecyclerView.Adapter<SingleImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewSingleImageBinding.inflate(inflater, parent, false)
        return SingleImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleImageViewHolder, position: Int) {
       holder.bind(imageUrl = imageUrlList[position])
    }

    override fun getItemCount(): Int = imageUrlList.size

    @SuppressLint("NotifyDataSetChanged")
    fun applyImageUrlData(newImageUrlList : List<String>) {
        imageUrlList = newImageUrlList
        notifyDataSetChanged()
    }
}