package com.example.megavision.utils.ext

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun getGsonInstance(): Gson {
    return GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
}

fun Any.getGSONString(): String {
    return try {
        getGsonInstance().toJson(this)
    } catch (e: Exception) {
        ""
    }
}