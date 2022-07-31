package com.softsquared.gridge_test.android.instagram_challenge.custom_view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.softsquared.gridge_test.android.instagram_challenge.R

class ViewLoadingDialog(context : Context) : Dialog(context) {
    init {
        setCanceledOnTouchOutside(false)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.view_loading_dialog)
    }
}