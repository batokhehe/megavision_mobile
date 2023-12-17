package com.example.megavision.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtils {

    const val REQUEST_STORAGE_PERMISSIONS = 1004
    const val REQUEST_READ_PHONE_STATE_PERMISSIONS = 101

    private fun Activity.requestStoragePermission() {
        val permissionList = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_STORAGE_PERMISSIONS
        )
    }

    fun Activity.checkReadPhoneStatePermission(permissionInvoke: () -> Unit?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestReadPhoneStatePermissions()
        } else {
            permissionInvoke.invoke()
        }
    }

    private fun Activity.requestReadPhoneStatePermissions() {
        val permissionList = arrayOf(
            Manifest.permission.READ_PHONE_STATE
        )
        ActivityCompat.requestPermissions(
            this,
            permissionList,
            REQUEST_READ_PHONE_STATE_PERMISSIONS
        )
    }
}
