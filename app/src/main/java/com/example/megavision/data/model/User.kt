package com.example.megavision.data.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("employee_id")
    var employeeId: String? = null
}