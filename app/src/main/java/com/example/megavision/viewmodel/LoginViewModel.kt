package com.example.megavision.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.megavision.data.api.ApiClient
import com.example.megavision.data.model.request.LoginRequest
import com.example.megavision.data.model.response.LoginResponse
import com.example.megavision.data.repository.LoginRepository
import com.example.megavision.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val context = getApplication<Application>()
    private val dataRepository: LoginRepository =
        LoginRepository(ApiClient.getClient(context))
    var responseData: MutableLiveData<LoginResponse>? = null
    var networkState: MutableLiveData<NetworkState>? = null

    fun hitLogin(email: String, password: String) {
        val body = LoginRequest()
        body.email = email
        body.password = password
        responseData = dataRepository.fetchLogin(compositeDisposable, context, body)
        networkState = dataRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}