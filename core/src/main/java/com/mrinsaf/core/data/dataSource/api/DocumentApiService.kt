package com.mrinsaf.core.data.dataSource.api

import com.mrinsaf.core.domain.model.api.response.DocumentInfoResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Streaming

interface DocumentApiService {
    @POST("/main/getInformation")
    @FormUrlEncoded
    suspend fun getDocumentInfo(
        @Field("personCode") personCode: Int,
        @Field("kksCode") kksCode: String,
        @Field("workType") workType: String,
        @Field("docType") docType: String,
        @Field("versionPrefix") versionPrefix: String,
        @Field("version") version: Int,
        @Field("dateInput") dateInput: String,
        @Field("dateCreate") dateCreate: String
    ): DocumentInfoResponse

    @POST("/main/getVersionDocument")
    @FormUrlEncoded
    @Streaming
    suspend fun downloadDocument(
        @Field("kksCode") kksCode: String,
        @Field("versionPrefix") versionPrefix: String,
        @Field("version") version: Int
    ): Response<ResponseBody>
}