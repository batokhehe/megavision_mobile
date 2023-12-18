package com.example.megavision.data.model

import com.google.gson.annotations.SerializedName

class Report {
    @SerializedName("count")
    var orderCount: String? = null

    @SerializedName("amount")
    var orderAmount: String? = null

    @SerializedName("list")
    var list: ArrayList<List>? = null
}