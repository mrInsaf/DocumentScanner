package com.mrinsaf.feature_document_details.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrinsaf.core.data.mapper.DocumentMapper
import com.mrinsaf.core.data.model.api.request.DocumentInfoRequest
import com.mrinsaf.core.data.model.api.response.DocumentInfoResponse
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.domain.repository.DocumentRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DocumentDetailsViewModel(
    private val repository: DocumentRepository,
    private val mapper: DocumentMapper = DocumentMapper
): ViewModel() {

    var documentInfo = MutableStateFlow<DocumentInfoResponse?>(null)
        private set

    fun onReviewDocumentClick(
        qrDocumentDetails: QrDocumentDetails
    ) = viewModelScope.launch {
        try {
            val request = mapper.mapQrDetailsToDownloadRequest(qrDocumentDetails)
            val documentByteCode = async { repository.downloadDocument(request) }.await()
            println("file download successful: $documentByteCode")
        } catch (e: IllegalArgumentException) {
            println(e)
        }
    }

    fun getDocumentInformation(
        kksCode: String,
        versionPrefix: String,
        version: Int,
    ) {
        viewModelScope.launch {
            val documentInformation = repository.getDocumentInfo(
                DocumentInfoRequest(
                    kksCode = kksCode,
                    versionPrefix = versionPrefix,
                    version = version
                )
            )
            documentInfo.value = documentInformation
        }
    }
}