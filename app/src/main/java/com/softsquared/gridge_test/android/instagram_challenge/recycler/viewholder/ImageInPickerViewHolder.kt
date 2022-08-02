package com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.ImagePickerData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewImageInPickerBinding

class ImageInPickerViewHolder(private val binding : ViewImageInPickerBinding, private val imageClickCallback : (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            imageClickCallback(bindingAdapterPosition)
        }
    }

    fun bind(imagePickerData: ImagePickerData){
        binding.imagePickerData = imagePickerData
    }
}