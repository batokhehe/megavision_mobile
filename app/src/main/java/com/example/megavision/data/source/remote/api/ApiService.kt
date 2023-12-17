package com.example.megavision.data.source.remote.api

import com.example.megavision.data.model.request.Login
import com.example.megavision.data.model.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Accept: application/json")
    @POST("api/login")
    fun login(
        @Body body: Login
    ): Response<BaseResponse>
}