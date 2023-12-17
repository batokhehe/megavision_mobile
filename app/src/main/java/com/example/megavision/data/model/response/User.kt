package com.example.megavision.data.model.response

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: User? = null
}