package com.example.megavision.domain.repository

import com.example.megavision.data.model.response.BaseResponse
import com.example.megavision.data.utils.Status
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun login(
        email: String,
        password: String,
    ): Flow<Status<BaseResponse>>
}