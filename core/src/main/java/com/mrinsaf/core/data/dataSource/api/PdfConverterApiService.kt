package com.mrinsaf.core.data.dataSource.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Streaming

interface PdfConverterApiService {
    @POST("convert")
    @Multipart
    @Streaming
    suspend fun convertDocxToPdf(
        @Part file: MultipartBody.Part
    ): Response<ResponseBody>
}