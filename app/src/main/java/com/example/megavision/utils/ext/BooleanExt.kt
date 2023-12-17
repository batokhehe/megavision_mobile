package com.example.megavision.utils.ext

fun Boolean?.toSafeBoolean(): Boolean {
    return try {
        this ?: false
    } catch (e: Exception) {
        false
    }
}