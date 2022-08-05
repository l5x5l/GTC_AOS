package com.softsquared.gridge_test.android.instagram_challenge.recycler.item_decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Grid3ItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view) % 3) {
            0 -> {
                outRect.right = 4
            }
            1 -> {
                outRect.left = 2
                outRect.right = 2
            }
            else -> { // 2
                outRect.left = 4
            }
        }
        outRect.bottom = 6

    }
}