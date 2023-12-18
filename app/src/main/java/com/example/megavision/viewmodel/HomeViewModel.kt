package com.example.megavision.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.megavision.data.api.ApiClient
import com.example.megavision.data.model.response.ReportResponse
import com.example.megavision.data.repository.HomeRepository
import com.example.megavision.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val context = getApplication<Application>()
    private val dataRepository: HomeRepository =
        HomeRepository(ApiClient.getClient(context))
    var responseData: MutableLiveData<ReportResponse>? = null
    var networkState: MutableLiveData<NetworkState>? = null

    fun hitReport() {
        responseData = dataRepository.fetchReport(compositeDisposable, context)
        networkState = dataRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}