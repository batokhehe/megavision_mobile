package com.example.megavision.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.megavision.data.model.response.LoginResponse

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
    val sessName: String?
        get() = pref.getString(SESS_NAME, "")
    val sessEmail: String?
        get() = pref.getString(SESS_NAME, "")

    fun setFromLogin(login: LoginResponse) {
        editor.putString(KEY_TOKEN, login.accessToken)
        editor.putString(SESS_NAME, login.user?.name)
        editor.putString(SESS_EMAIL, login.user?.email)
        editor.commit()
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val KEY_TOKEN = "token"
        private const val SESS_NAME = "name"
        private const val SESS_EMAIL = "email"
    }
}