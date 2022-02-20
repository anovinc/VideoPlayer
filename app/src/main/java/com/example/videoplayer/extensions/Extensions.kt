package com.example.videoplayer.extensions

import android.view.View

inline fun View.onClick(crossinline onClick: () -> Unit) {
    this.setOnClickListener {
        onClick()
    }
}

inline fun View.invisble() {
    visibility = View.INVISIBLE
}

inline fun View.visible() {
    visibility = View.VISIBLE
}