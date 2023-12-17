package com.example.megavision.utils.ext

import android.app.Activity
import android.content.Intent
import com.example.megavision.view.HomeActivity
import com.example.megavision.view.LoginActivity

fun Activity.goToHomeActivity() {
    val intent = Intent(this, HomeActivity::class.java)
    startActivity(intent)
    finish()
}

fun Activity.goToLoginActivity() {
    val intent = Intent(this, LoginActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
    finish()
}