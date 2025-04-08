package com.mrinsaf.core.data.dataSource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://mr-morkow.ru:8888/document_api/QRCodeFactory/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val documentApiService: DocumentApiService = retrofit.create(DocumentApiService::class.java)
}