package com.mrinsaf.core.data.mapper

import com.mrinsaf.core.data.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.domain.model.QrDocumentDetails

object DocumentMapper {

    fun mapQrDetailsToDownloadRequest(details: QrDocumentDetails): DocumentDownloadRequest {
        return DocumentDownloadRequest(
            kksCode = details.kksCode,
            versionPrefix = details.versionPrefix ?: throw IllegalArgumentException("versionPrefix не может быть null"),
            version = details.version ?: throw IllegalArgumentException("version не может быть null")
        )
    }
}