package com.mrinsaf.feature_scanner.ui.viewModel

import com.mrinsaf.core.domain.model.QrDocumentDetails

data class ScannerUiState(
    var qrResult: String = "",
    var parsedQr: QrDocumentDetails? = null,
)
