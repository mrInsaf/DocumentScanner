package com.mrinsaf.feature_scanner.model

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class CameraController(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner
) : DefaultLifecycleObserver {

    private var cameraProvider: ProcessCameraProvider? = null
    private var imageAnalysis: ImageAnalysis? = null
    private var onQRDetected: ((String) -> Unit)? = null

    fun setOnQRCodeDetectedListener(listener: (String) -> Unit) {
        this.onQRDetected = listener
    }

    fun start(previewView: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            this.cameraProvider = cameraProviderFuture.get()
            bindCameraUseCases(previewView)
        }, ContextCompat.getMainExecutor(context))
    }

    private fun bindCameraUseCases(previewView: PreviewView) {
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also { it.setAnalyzer(ContextCompat.getMainExecutor(context), QRCodeAnalyzer { qrValue ->
                onQRDetected?.invoke(qrValue)
            }) }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        try {
            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            )
        } catch (exc: Exception) {
            Log.e("CameraController", "Binding failed", exc)
        }
    }

    fun release() {
        cameraProvider?.unbindAll()
        imageAnalysis?.clearAnalyzer()
    }
}