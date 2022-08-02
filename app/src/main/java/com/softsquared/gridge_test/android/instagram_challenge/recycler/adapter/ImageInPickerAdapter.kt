package com.softsquared.gridge_test.android.instagram_challenge.recycler.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.ImagePickerData
import com.softsquared.gridge_test.android.instagram_challenge.databinding.ViewImageInPickerBinding
import com.softsquared.gridge_test.android.instagram_challenge.recycler.viewholder.ImageInPickerViewHolder

class ImageInPickerAdapter(private val imageUriList : ArrayList<ImagePickerData>, private val itemClickCallback : (Int) -> Unit) : RecyclerView.Adapter<ImageInPickerViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageInPickerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewImageInPickerBinding.inflate(inflater, parent, false)
        return ImageInPickerViewHolder(binding, itemClickCallback)
    }

    override fun onBindViewHolder(holder: ImageInPickerViewHolder, position: Int) {
        holder.bind(imageUriList[position])
    }

    override fun getItemCount(): Int = imageUriList.size

    fun applyChangeCurrentPosition(currentPosition : Int) {
        if (currentPosition != selectedPosition) {
            if (selectedPosition >= 0) {
                imageUriList[selectedPosition].isSelected = false
            }
            imageUriList[currentPosition].isSelected = true
            notifyItemChanged(selectedPosition)
            notifyItemChanged(currentPosition)
            selectedPosition = currentPosition
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun applyAllData(){
        notifyDataSetChanged()
    }
}