package com.example.megavision.data.api

interface ApiService {
    fun <T> getService(clazz:Class<T>): T
}