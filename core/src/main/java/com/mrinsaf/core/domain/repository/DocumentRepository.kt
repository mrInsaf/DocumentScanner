package com.mrinsaf.core.domain.repository

import com.mrinsaf.core.data.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.data.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse

interface DocumentRepository {
    suspend fun getDocumentInfo(params: DocumentInfoRequest): DocumentInfoResponse
    suspend fun downloadDocument(params: DocumentDownloadRequest): ByteArray

    suspend fun register(login: String, password: String): String
    suspend fun login(login: String, password: String): String

    fun convertDocxToPdf(file: ByteArray): ByteArray
}