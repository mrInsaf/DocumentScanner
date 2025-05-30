package com.mrinsaf.core.data.dataSource.api

import com.mrinsaf.core.common.PDF_CONVERTER_BASE_URL
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PdfConverterRetrofitClient {

    // Добавляем логгер
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Кастомный CookieJar для сохранения сессий
    private val cookieJar = object : CookieJar {
        private val cookies = mutableListOf<Cookie>()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            this.cookies.addAll(cookies)
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookies.filter { it.matches(url) }
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Подключаем перехватчик
        .cookieJar(cookieJar)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(PDF_CONVERTER_BASE_URL)
        .client(okHttpClient) // Указываем кастомный клиент
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val pdfConverterApiService: PdfConverterApiService = retrofit.create(PdfConverterApiService::class.java)
}