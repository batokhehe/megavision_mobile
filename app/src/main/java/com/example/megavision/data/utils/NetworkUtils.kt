package com.example.megavision.data.utils

import android.content.Context
import android.widget.Toast
import com.example.megavision.utils.CheckInternet

object NetworkUtils {

    fun createHeaderBearerToken(token: String): MutableMap<String, String> {
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = "Bearer $token"
        return map
    }

    fun Context.checkNetwork(onInternetCheck: OnInternetCheck) {
        if (CheckInternet.isNetwork(this)) {
            onInternetCheck.internetAvailable()
        } else {
            Toast.makeText(this, "No Internet Access.", Toast.LENGTH_SHORT).show()
        }
    }

    interface OnInternetCheck {
        fun internetAvailable()
    }

}