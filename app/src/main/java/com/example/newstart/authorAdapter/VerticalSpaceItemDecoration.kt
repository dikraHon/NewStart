package com.example.newstart.authorAdapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    private val spaceHeight: Int,
    private val excludeFirst: Boolean = true,
    private val excludeLast: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val itemCount = state.itemCount

        with(outRect) {
            top = if (position == 0 && excludeFirst) 0 else spaceHeight / 2
            bottom = if (position == itemCount - 1 && excludeLast) 0 else spaceHeight / 2
        }
    }
}