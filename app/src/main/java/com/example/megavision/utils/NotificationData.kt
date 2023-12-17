package com.example.megavision.utils

import android.content.Intent

data class NotificationData(
    var title: String,
    var content: String,
    var intentDestination: Intent
)