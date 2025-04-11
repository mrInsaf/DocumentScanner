package com.mrinsaf.core.data.model.api.request

data class DocumentInfoRequest(
    val kksCode: String,
    val versionPrefix: String,
    val version: Int
)