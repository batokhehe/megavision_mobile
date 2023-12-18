package com.example.megavision.utils

import android.content.Context

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,

}

open class NetworkState(val status: Status, val msg: String = "") {

    companion object {

        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Something went wrong")
        val ENDOFLIST: NetworkState = NetworkState(Status.FAILED, "You have reached the end")
    }

    fun getNetworkHeader(context: Context): Map<String, String> {
        val sessionManager = SessionManager(context)
        val map: HashMap<String, String> = HashMap()
        map["Authorization"] = "Bearer " + sessionManager.keyToken
        map["Accept"] = "application/json"
        map["Content-type"] = "application/json"
        return map
    }
}