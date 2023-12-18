package com.example.megavision.data.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.megavision.data.api.RestApi
import com.example.megavision.data.model.request.LoginRequest
import com.example.megavision.data.model.response.LoginResponse
import com.example.megavision.data.model.response.ReportResponse
import com.example.megavision.utils.NetworkState
import com.example.megavision.utils.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
A data source class for retrieving policy details and performing related operations.
@param apiService The API service for retrieving policy details.
@param compositeDisposable The composite disposable for managing disposable resources.
 */

class HomeDataSource(
    private val apiService: RestApi,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: MutableLiveData<NetworkState>
        get() = _networkState

    private val _response = MutableLiveData<ReportResponse>()
    val loginResponse: MutableLiveData<ReportResponse>
        get() = _response

    /**
    Fetches the Login details from the API.
    @param context The application context.
    @param policyNo The policy number.
     */
    fun fetchReport(context: Context) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.report(NetworkState(Status.RUNNING).getNetworkHeader(context))
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