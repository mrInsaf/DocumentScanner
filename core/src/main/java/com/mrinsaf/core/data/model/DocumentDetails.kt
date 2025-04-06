package com.mrinsaf.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DocumentDetails(
    val senderCode: String,
    val kksCode: String,
    val workType: String,
    val documentType: String,
    val documentVersion: String,
    val uploadDate: String,
)