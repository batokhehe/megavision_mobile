package com.example.megavision.utils.ext

import android.view.View

fun View?.hideView() {
    this?.visibility = View.GONE
}

fun View?.showView() {
    this?.visibility = View.VISIBLE
}

fun View?.showView(isShow: Boolean) {
    if (isShow) {
        this?.showView()
    } else {
        this?.hideView()
    }
}
