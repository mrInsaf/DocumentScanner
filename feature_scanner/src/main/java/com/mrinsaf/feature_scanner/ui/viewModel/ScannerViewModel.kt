package com.mrinsaf.feature_scanner.ui.viewModel

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mrinsaf.core.domain.model.DocumentDetails
import com.mrinsaf.core.data.mapper.QrParser
import com.mrinsaf.feature_scanner.data.hardware.CameraController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScannerViewModel : ViewModel() {

    var uiState = MutableStateFlow(ScannerUiState())
        private set

    private var cameraController: CameraController? = null

    fun initCamera(context: Context, lifecycleOwner: LifecycleOwner, previewView: PreviewView) {
        cameraController = CameraController(context, lifecycleOwner).apply {
            setOnQRCodeDetectedListener { qrValue ->
                uiState.update { it.copy(qrResult = qrValue) }
            }
            start(previewView)
        }
    }

    fun parseQrCode(rawQrData: String) {
        println(rawQrData)
        try {
            if (rawQrData.isNotBlank()) {
                val parsedData = QrParser.parse(rawQrData)
                onQrParsed(parsedData)
            }
        }
        catch (e: Exception) {
            println(e)
            onQrParseFailure()
        }
    }

    private fun onQrParsed(parsedQr: DocumentDetails) {
        uiState.update { it.copy(parsedQr = parsedQr) }
    }

    private fun onQrParseFailure() {
        uiState.update { it.copy(parsedQr = null) }
    }

    override fun onCleared() {
        cameraController?.release()
        super.onCleared()
    }
}