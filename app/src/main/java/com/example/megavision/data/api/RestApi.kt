package com.example.megavision.data.api

import com.example.megavision.data.model.request.LoginRequest
import com.example.megavision.data.model.response.LoginResponse
import com.example.megavision.data.model.response.ReportResponse
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {

    @Headers("Accept: application/json")
    @GET("report")
    fun report(@HeaderMap headers: Map<String, String>): Observable<ReportResponse>

    @Headers("Accept: application/json")
    @POST("auth/login")
    fun login(@Body requestBody: LoginRequest): Observable<LoginResponse>
}