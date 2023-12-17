package com.example.megavision.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.megavision.data.model.request.Login
import com.example.megavision.data.model.response.BaseResponse

class SessionManager(var c: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var privateMode = 0
    var prefName = "MegavisionPreff"

    init {
        pref = c.getSharedPreferences(prefName, privateMode)
        editor = pref.edit()
    }

    var keyToken: String?
        get() = pref.getString(KEY_TOKEN, "")
        set(keyToken) {
            editor.putString(KEY_TOKEN, keyToken)
            editor.commit()
        }
    val sessUsername: String?
        get() = pref.getString(SESS_USERNAME, "")
    val sessName: String?
        get() = pref.getString(SESS_NAME, "")

    fun setFromLogin(login: BaseResponse) {
        editor.putString(KEY_TOKEN, login.access_token)
        editor.putString(SESS_USERNAME, login.user?.name)
        editor.commit()
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val KEY_TOKEN = "token"
        private const val SESS_USERNAME = "userName"
        private const val SESS_NAME = "name"
    }
}