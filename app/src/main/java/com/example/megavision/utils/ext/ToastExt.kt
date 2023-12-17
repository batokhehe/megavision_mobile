package com.example.megavision.utils.ext

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showToast(
    message: String? = null,
    messageId: Int? = null,
    durations: Int = Toast.LENGTH_LONG
) {
    val content = message ?: if (messageId != null) {
        getString(messageId)
    } else {
        ""
    }
    Toast.makeText(this, content, durations).show()
}

fun Activity.showToast(
    message: String? = null,
    messageId: Int? = null,
    durations: Int = Toast.LENGTH_LONG
) {
    val content = message ?: if (messageId != null) {
        getString(messageId)
    } else {
        ""
    }
    Toast.makeText(this, content, durations).show()
}

fun Fragment.showToast(
    message: String? = null,
    messageId: Int? = null,
    durations: Int = Toast.LENGTH_LONG
) {
    val content = message ?: if (messageId != null) {
        getString(messageId)
    } else {
        ""
    }
    Toast.makeText(this.requireContext(), content, durations).show()
}