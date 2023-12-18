package com.example.megavision.data.datasource

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.megavision.data.api.RestApi
import com.example.megavision.data.model.request.LoginRequest
import com.example.megavision.data.model.response.LoginResponse
import com.example.megavision.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
A data source class for retrieving policy details and performing related operations.
@param apiService The API service for retrieving policy details.
@param compositeDisposable The composite disposable for managing disposable resources.
 */

class LoginDataSource(
    private val apiService: RestApi,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: MutableLiveData<NetworkState>
        get() = _networkState

    private val _response = MutableLiveData<LoginResponse>()
    val loginResponse: MutableLiveData<LoginResponse>
        get() = _response

    /**
    Fetches the Login details from the API.
    @param context The application context.
    @param policyNo The policy number.
     */
    fun fetchLogin(context: Context, request: LoginRequest) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.login(request)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _response.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                        }
                    )
            )
        } catch (e: Exception) {
            _networkState.postValue(NetworkState.ERROR)
        }
    }
}