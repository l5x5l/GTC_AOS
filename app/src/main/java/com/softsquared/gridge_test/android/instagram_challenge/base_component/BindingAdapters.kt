package com.softsquared.gridge_test.android.instagram_challenge.base_component

import android.text.Html
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.custom_view.ViewLoginEditText
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.StringWrapper
import com.softsquared.gridge_test.android.instagram_challenge.utils.getTimeDiffFromCurrent

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("innerText")
    fun setInnerText(view : ViewLoginEditText, stringWrapper: StringWrapper) {
        view.bind(stringWrapper)
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun setErrorMessage(view : ViewLoginEditText, errorString : String = "") {
        view.setErrorMessage(errorString)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageByUrl(view : AppCompatImageView, imageUrl : String ?= null) {
        imageUrl?.let {
            Glide.with(view).load(it).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("thumbnailImageUrl")
    fun setThumbnailImageByUrl(view : AppCompatImageView, imageUrl : String ?= null) {
        imageUrl?.let {
            Glide.with(view).load(it).override(180, 180).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun setVisibilityByBoolean(view : View, isVisible : Boolean = false){
        view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter(value = [ "userId", "content" ])
    fun setUserAndContent(view : AppCompatTextView, userId : String, context : String) {
        view.text = Html.fromHtml("<b>$userId</b> $context", Html.FROM_HTML_MODE_LEGACY)
    }

    @JvmStatic
    @BindingAdapter("createdAt")
    fun setCreatedAtString(view : AppCompatTextView, createdAt : String) {
        view.text = getTimeDiffFromCurrent(createdAt, view.context)
    }
}