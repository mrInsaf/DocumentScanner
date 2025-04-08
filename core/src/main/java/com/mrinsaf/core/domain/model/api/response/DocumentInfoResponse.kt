package com.mrinsaf.core.domain.model.api.response

data class DocumentInfoResponse(
    val document: String,
    val kksCode: String,
    val versionPrefix: String,
    val version: Int,
    val newVersion: Int
)