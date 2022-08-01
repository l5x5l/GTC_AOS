package com.softsquared.gridge_test.android.instagram_challenge.utils

import android.content.Context
import android.util.TypedValue

fun dpToPx(context : Context, dp : Int) : Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
}