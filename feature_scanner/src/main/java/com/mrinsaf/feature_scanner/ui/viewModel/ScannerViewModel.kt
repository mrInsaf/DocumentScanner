package com.mrinsaf.feature_scanner.ui.viewModel

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mrinsaf.feature_scanner.model.CameraController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScannerViewModel : ViewModel() {

    private val _qrResult = MutableStateFlow<String?>(null)
    val qrResult: StateFlow<String?> = _qrResult

    private var cameraController: CameraController? = null

    fun initCamera(context: Context, lifecycleOwner: LifecycleOwner, previewView: PreviewView) {
        cameraController = CameraController(context, lifecycleOwner).apply {
            setOnQRCodeDetectedListener { qrValue ->
                _qrResult.value = qrValue
            }
            start(previewView)
        }
    }

    fun resetQRResult() {
        _qrResult.value = null
    }

    override fun onCleared() {
        cameraController?.release()
        super.onCleared()
    }
}