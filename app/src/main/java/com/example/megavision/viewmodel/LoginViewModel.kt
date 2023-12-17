package com.example.megavision.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.megavision.data.model.response.BaseResponse
import com.example.megavision.data.model.response.User
import com.example.megavision.data.utils.Status
import com.example.megavision.domain.repository.AppRepository
import com.example.megavision.utils.CheckInternet
import com.example.megavision.utils.NetworkStatus
import com.example.megavision.utils.Resource
import com.example.megavision.utils.Result
import com.example.megavision.utils.SessionManager
import com.example.megavision.utils.ToastType
import kotlinx.coroutines.launch

class LoginViewModel(
    private val application: Application,
    private val sessionManager: SessionManager,
    private val appRepository: AppRepository
) : BaseViewModel(application, sessionManager) {

    private val _networkStatusLiveData = MutableLiveData<NetworkStatus>()
    val networkStatusLiveData: LiveData<NetworkStatus> = _networkStatusLiveData

    private val _loginResultLiveData = MutableLiveData<Result>()
    val loginResultLiveData: LiveData<Result> = _loginResultLiveData

    private lateinit var email: String
    private lateinit var password: String
    private var inProgress: Boolean = false

    fun login(email: String, password: String) {
        when (networkStatusLiveData.value ?: NetworkStatus.UNAVAILABLE) {
            NetworkStatus.UNAVAILABLE -> {
                showToastLiveData.value =
                    Resource(ToastType.STRING_ID, "Wrong Credentials.")
            }

            NetworkStatus.AVAILABLE -> {
                if (CheckInternet.isNetwork(application)) {
                    callLogin(email, password)
                } else {
                    showToastLiveData.value =
                        Resource(ToastType.STRING_ID, "No Internet Access.")
                }
            }
        }
    }

    fun setNetworkStatus(networkStatus: NetworkStatus) {
        _networkStatusLiveData.value = networkStatus
    }

    private fun callLogin(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            appRepository.login(
                email = email,
                password = password,
            ).collect { response ->
                when (response) {
                    is Status.Loading -> {
                    }

                    is Status.Success -> {
                        insertData(response)
                    }

                    is Status.Error -> {
                        showLoadingLiveData.value = false
                        showToastLiveData.value =
                            Resource(ToastType.STRING, "Internal Server Error")
                    }

                    else -> {
                        showLoadingLiveData.value = false
                        showToastLiveData.value =
                            Resource(ToastType.STRING, "No Internet Access.")
                    }
                }
            }
        }
    }

    private fun insertData(
        response: Status<BaseResponse>,
    ) {
        val data = BaseResponse()
        data.user = User()
        data.user!!.id = response.data?.user?.id
        data.user!!.email = response.data?.user?.email
        data.user!!.name = response.data?.user?.name
        sessionManager.setFromLogin(data)

        navigateNextPage()
    }

    private fun navigateNextPage() {
        _loginResultLiveData.value = Result.GO_TO_HOME
    }
}