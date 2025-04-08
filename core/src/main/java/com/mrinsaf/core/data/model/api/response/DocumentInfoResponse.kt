package com.mrinsaf.core.data.model.api.response

data class DocumentInfoResponse(
    val document: String,
    val kksCode: String,
    val versionPrefix: String,
    val version: Int,
    val newVersion: Int
)