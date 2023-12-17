package com.example.megavision.data.model.response

import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("access_token")
    var access_token: String? = null

    @SerializedName("user")
    var user: User? = null
}
