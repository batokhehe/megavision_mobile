package com.example.megavision.utils.ext

fun Int?.toSafeInt(): Int {
    return try {
        this ?: 0
    } catch (e: Exception) {
        0
    }
}

fun Int?.toSafeString(): String {
    return try {
        this.toString()
    } catch (e: Exception) {
        ""
    }
}