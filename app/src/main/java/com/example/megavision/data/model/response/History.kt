package com.example.megavision.data.model.response

import com.google.gson.annotations.SerializedName

class History {
    @SerializedName("client")
    var client: String? = null

    @SerializedName("amount")
    var amount: String? = null
}