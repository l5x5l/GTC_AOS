package com.softsquared.gridge_test.android.instagram_challenge.repository

import android.content.Context
import android.provider.MediaStore
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.ImagePickerData

class LocalImageRepository(private val context : Context) {

    fun getImageUrls() : ArrayList<ImagePickerData> {
        val imageList = ArrayList<ImagePickerData>()

        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA), null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        when (cursor?.count) {
            null -> {
                throw UnknownError()
            }
            else -> {
                val columnIdx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                while(cursor.moveToNext()) {
                    val id = cursor.getString(columnIdx)
                    imageList.add(ImagePickerData(imageUrl = id.toString(), isSelected = false))
                }
                cursor.close()
            }
        }

        return imageList
    }
}