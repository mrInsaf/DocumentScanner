package com.mrinsaf.core.domain.repository.api

import com.mrinsaf.core.domain.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.domain.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.domain.model.api.response.DocumentInfoResponse

interface DocumentRepository {
    suspend fun getDocumentInfo(params: DocumentInfoRequest): DocumentInfoResponse
    suspend fun downloadDocument(params: DocumentDownloadRequest): ByteArray
}