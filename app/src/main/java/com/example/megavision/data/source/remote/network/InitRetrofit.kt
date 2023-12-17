package com.example.megavision.data.source.remote.network

import android.content.Context
import com.example.megavision.utils.Const
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class InitRetrofit() {
    private lateinit var loggingInterceptor: HttpLoggingInterceptor
    private var httpClientBuilder: Builder? = null
    var instance: InitRetrofit? = null
    private var retrofit: Retrofit? = null

    constructor(context: Context) : this() {
        httpClientBuilder = Builder()
        loggingInterceptor =
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        httpClientBuilder = Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(loggingInterceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL_BACKEND)
            .client(httpClientBuilder!!.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun getInstance(context: Context): InitRetrofit {
        if (instance == null) {
            instance = InitRetrofit(context)
        }
        return instance as InitRetrofit
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit!!.create(serviceClass)
    }
}