package com.example.megavision.data.model.response

import com.example.megavision.data.model.Report
import com.google.gson.annotations.SerializedName

class ReportResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("error")
    var error: String? = null

    @SerializedName("data")
    var data: Report? = null
}