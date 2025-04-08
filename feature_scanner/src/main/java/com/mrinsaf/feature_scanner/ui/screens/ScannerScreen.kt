package com.mrinsaf.feature_scanner.ui.screens

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.presentation.ui.components.MainButton
import com.mrinsaf.core.presentation.ui.screens.BasicScreen
import com.mrinsaf.feature_scanner.R
import com.mrinsaf.feature_scanner.ui.components.ScanStatusText
import com.mrinsaf.feature_scanner.ui.viewModel.ScannerViewModel

@Composable
fun ScannerScreen(
    viewModel: ScannerViewModel = viewModel(),
    onShowDocumentDetailsClick: (QrDocumentDetails) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val qrResult = uiState.value.qrResult
    val parsedQr = uiState.value.parsedQr

    LaunchedEffect(Unit) {
        viewModel.initCamera(context, lifecycleOwner, previewView)
    }

    LaunchedEffect(qrResult) {
        viewModel.parseQrCode(qrResult)
    }

    BasicScreen(
        title = "Сканер"
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
//                .border(width = 2.dp, color = Color.Red)
        ) {
            Text(
                text = stringResource(R.string.scan_code),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { previewView },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
            ) {
                ScanStatusText(parsedQr = parsedQr)

                Spacer(Modifier.height(12.dp))

                MainButton(
                    text = "Посмотреть детали",
                    enabled = parsedQr != null,
                    onClick = {
                        onShowDocumentDetailsClick(parsedQr!!)
                    }
                )
            }

        }
    }
}
