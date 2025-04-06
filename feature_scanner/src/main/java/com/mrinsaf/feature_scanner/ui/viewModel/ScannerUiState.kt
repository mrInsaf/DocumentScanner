package com.mrinsaf.feature_scanner.ui.viewModel

import com.mrinsaf.core.data.model.DocumentDetails

data class ScannerUiState(
    var qrResult: String = "",
    var parsedQr: DocumentDetails? = null,
)
