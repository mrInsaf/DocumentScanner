package com.mrinsaf.core.data.model.api.request

data class DocumentInfoRequest(
    val personCode: Int,
    val kksCode: String,
    val workType: String,
    val docType: String,
    val versionPrefix: String,
    val version: Int,
    val dateInput: String,
    val dateCreate: String
)