package com.mrinsaf.core.data.dataSource.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://mr-morkow.ru:8888/document_api/QRCodeFactory/"

    // Добавляем логгер
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Уровень логирования
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Подключаем перехватчик
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) // Указываем кастомный клиент
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val documentApiService: DocumentApiService = retrofit.create(DocumentApiService::class.java)
}