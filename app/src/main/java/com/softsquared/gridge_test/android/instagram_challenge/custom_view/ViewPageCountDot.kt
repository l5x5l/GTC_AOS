package com.softsquared.gridge_test.android.instagram_challenge.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.utils.dpToPx
import kotlin.math.min

class ViewPageCountDot(context: Context, attrs : AttributeSet) : LinearLayout(context, attrs) {
    private var currentItemPosition = 0
    private var pageAmount = 1
    private val dotSize = dpToPx(context, 6)
    private val marginSize = dpToPx(context, 2)
    private val dotList = ArrayList<View>()

    fun clearAllChild(){
        dotList.clear()
        removeAllViews()
    }

    fun applyItemCount(amount : Int) {
        pageAmount = min(amount, 5)
        if (pageAmount <= 1) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            for (i in 0 until pageAmount) {
                val view = View(context)
                val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(marginSize, 0, marginSize, 0)
                view.layoutParams = layoutParams
                view.layoutParams.height = dotSize
                view.layoutParams.width = dotSize
                view.background = if (i == 0) {
                    ContextCompat.getDrawable(context, R.drawable.shape_dot_active)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.shape_dot_deactive)
                }
                dotList.add(view)
                addView(view)
            }
        }
    }

    fun changeItem(targetPosition : Int) {
        val tempTargetPosition = if (targetPosition >= 5) {
             4
        } else { targetPosition }

        if (tempTargetPosition != currentItemPosition) {
            dotList[currentItemPosition].background = ContextCompat.getDrawable(context, R.drawable.shape_dot_deactive)
            dotList[tempTargetPosition].background = ContextCompat.getDrawable(context, R.drawable.shape_dot_active)
            currentItemPosition = tempTargetPosition
        }
    }
}