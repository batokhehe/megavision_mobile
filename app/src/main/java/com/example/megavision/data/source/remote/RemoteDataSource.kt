package com.example.megavision.data.source.remote

import com.example.megavision.data.model.request.Login
import com.example.megavision.data.model.response.BaseResponse
import com.example.megavision.data.source.remote.api.ApiService
import com.example.megavision.data.source.remote.network.ApiResponse
import com.example.megavision.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

class RemoteDataSource(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    fun login(body: Login): Flow<ApiResponse<BaseResponse>> {
        return flow {
            handleResponse(
                response = apiService.login(
                    body = body
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun <T> FlowCollector<ApiResponse<T>>.handleResponse(
        response: Response<T>
    ) {
        try {
            when {
                response.isSuccessful -> {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        emit(ApiResponse.Success(responseBody))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }

                response.code() == 400 || response.code() == 500 -> {
                    emit(ApiResponse.Error(response.errorBody().toString()))
                }

                else -> {
                    emit(ApiResponse.Empty)
                }
            }
        } catch (he: HttpException) {
            emit(ApiResponse.HttpError(he))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }

}