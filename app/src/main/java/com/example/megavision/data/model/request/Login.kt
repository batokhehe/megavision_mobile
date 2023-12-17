package com.example.megavision.data.model.request

import com.example.megavision.data.model.response.User
import com.google.gson.annotations.SerializedName

class Login(email: String, password: String) {

    @SerializedName("email")
    var email: User? = null

    @SerializedName("password")
    var password: String? = null

}