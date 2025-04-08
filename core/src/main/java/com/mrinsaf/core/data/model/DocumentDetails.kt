package com.mrinsaf.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DocumentDetails(
    val personCode: Int,
    val kksCode: String,
    val workType: String? = null,
    val docType: String? = null,
    val versionPrefix: String? = null,
    val version: Int? = null,
    val dateInput: String,
    val dateCreate: String
)