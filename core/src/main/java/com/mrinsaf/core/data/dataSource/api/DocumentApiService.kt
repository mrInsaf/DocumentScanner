package com.mrinsaf.core.data.dataSource.api

import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Streaming

interface DocumentApiService {
    @POST("main/getInformation")
    @FormUrlEncoded
    suspend fun getDocumentInfo(
        @Field("kksCode") kksCode: String,
        @Field("versionPrefix") versionPrefix: String,
        @Field("version") version: Int,
    ): DocumentInfoResponse

    @POST("main/getNewVersionDocument")
    @FormUrlEncoded
    @Streaming
    suspend fun downloadDocument(
        @Field("kksCode") kksCode: String,
        @Field("versionPrefix") versionPrefix: String,
        @Field("version") version: Int
    ): Response<ResponseBody>

    @POST("login/registration")
    @FormUrlEncoded
    suspend fun register(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<ResponseBody>

    @POST("login/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<ResponseBody>
}