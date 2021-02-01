package com.example.android.gdgfinder

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.gdgfinder.network.GdgApiStatus
import com.example.android.gdgfinder.network.GdgChapter
import com.example.android.gdgfinder.search.GdgListAdapter

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GdgChapter>?) {
    val adapter = recyclerView.adapter as GdgListAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("showOnlyWhenEmpty")
fun View.showOnlyWhenEmpty(data: List<GdgChapter>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("gdgApiStatus")
fun bindStatus(statusImageView: ImageView, status: GdgApiStatus?) {
    when (status) {
        GdgApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_launcher_foreground)
        }
        GdgApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.logo)
        }
        GdgApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}