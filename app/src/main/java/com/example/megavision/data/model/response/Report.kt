package com.example.megavision.data.model.response

import com.google.gson.annotations.SerializedName

class Report {
    @SerializedName("income")
    var income: String? = null

    @SerializedName("sales")
    var sales: String? = null

    @SerializedName("history")
    var history: ArrayList<History?> = ArrayList()
}