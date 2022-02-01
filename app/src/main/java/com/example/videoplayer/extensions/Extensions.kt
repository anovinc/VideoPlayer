package com.example.videoplayer.extensions

import android.view.View

inline fun View.onClick(crossinline onClick: () -> Unit) {
    this.setOnClickListener {
        onClick()
    }
}