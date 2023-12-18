package com.example.megavision.data.model.response

import com.example.megavision.data.model.User
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("user")
    var user: User? = null
}