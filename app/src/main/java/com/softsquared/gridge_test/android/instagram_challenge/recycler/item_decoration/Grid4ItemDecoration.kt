package com.softsquared.gridge_test.android.instagram_challenge.recycler.item_decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Grid4ItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view) % 4) {
            0 -> {
                outRect.right = 3
            }
            1 -> {
                outRect.left = 1
                outRect.right = 2
            }
            2 -> {
                outRect.left = 2
                outRect.right = 1
            }
            else -> { // 3
                outRect.left = 3
            }
        }
        outRect.bottom = 4

    }
}