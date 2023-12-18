package com.example.megavision.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.megavision.data.api.RestApi
import com.example.megavision.data.datasource.LoginDataSource
import com.example.megavision.data.model.request.LoginRequest
import com.example.megavision.data.model.response.LoginResponse
import com.example.megavision.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

/**
Repository class for fetching policy details.
@param apiService The API service for fetching policy details.
 */
class LoginRepository(private val apiService: RestApi) {

    lateinit var dataSource: LoginDataSource

    /**
    Fetches the policy detail for a given policy.
    @param compositeDisposable The composite disposable for managing disposable resources.
    @param context The application context.
    @param policyNo The policy number.
    @return MutableLiveData containing the policy detail response.
     */
    fun fetchLogin(
        compositeDisposable: CompositeDisposable,
        context: Context,
        request: LoginRequest
    ): MutableLiveData<LoginResponse> {

        dataSource = LoginDataSource(apiService, compositeDisposable)
        dataSource.fetchLogin(context, request)

        return dataSource.loginResponse
    }

    fun getNetworkState(): MutableLiveData<NetworkState> {
        return dataSource.networkState
    }
}