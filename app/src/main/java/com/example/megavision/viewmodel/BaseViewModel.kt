package com.example.megavision.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.megavision.utils.Resource
import com.example.megavision.utils.SessionManager

open class BaseViewModel(
    private val application: Application,
    private val sessionManager: SessionManager = SessionManager(application)
) : AndroidViewModel(application) {

    val showToastLiveData = MutableLiveData<Resource<Any>>()
    val showLoadingLiveData = MutableLiveData<Boolean>()

    fun showLoading() {
        showLoadingLiveData.value = true
    }

    fun hideLoading() {
        showLoadingLiveData.value = false
    }

}
