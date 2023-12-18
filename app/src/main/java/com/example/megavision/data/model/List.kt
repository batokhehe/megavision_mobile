package com.example.megavision.data.model

import com.google.gson.annotations.SerializedName

class List {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("client_name")
    var clientName: String? = null

    @SerializedName("order_amount")
    var orderAmount: String? = null
}