package com.mrinsaf.core.domain.model.api.request

data class DocumentDownloadRequest(
    val kksCode: String,
    val versionPrefix: String,
    val version: Int
)