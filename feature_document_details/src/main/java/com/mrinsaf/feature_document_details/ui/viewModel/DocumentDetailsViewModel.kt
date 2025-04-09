package com.mrinsaf.feature_document_details.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrinsaf.core.data.mapper.DocumentMapper
import com.mrinsaf.core.data.model.api.request.DocumentDownloadRequest
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.domain.repository.DocumentRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DocumentDetailsViewModel(
    private val repository: DocumentRepository,
    private val mapper: DocumentMapper = DocumentMapper
): ViewModel() {

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
}