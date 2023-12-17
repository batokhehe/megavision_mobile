package com.example.megavision.data.source

import com.example.megavision.data.model.request.Login
import com.example.megavision.data.model.response.BaseResponse
import com.example.megavision.data.source.remote.network.ApiResponse
import com.example.megavision.data.source.remote.RemoteDataSource
import com.example.megavision.data.utils.Status
import com.example.megavision.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : AppRepository {

    override fun login(
        email: String,
        password: String,
    ): Flow<Status<BaseResponse>> =
        channelFlow {
            val body = Login(email, password)
            val apiResponse = remoteDataSource.login(
                body = body,
            ).first()
            val status = handleApiResponse(apiResponse)
            send(status)
        }

    private fun <T> handleApiResponse(apiResponse: ApiResponse<T>): Status<T> {
        when (apiResponse) {
            is ApiResponse.Success -> {
                return Status.Success(apiResponse.data)
            }

            is ApiResponse.Error -> {
                return Status.Error(
                    message = apiResponse.errorMessage,
                    errorCode = apiResponse.errorCode,
                    messageId = apiResponse.errorMessageID,
                )

            }

            is ApiResponse.HttpError -> {
                return Status.HttpError(httpError = apiResponse.httpError)
            }

            else -> {
                return Status.Error("Unexpected Error loginAuth0B2BNew")
            }
        }
    }
}