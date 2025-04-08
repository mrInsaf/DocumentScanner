package com.mrinsaf.feature_scanner.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrinsaf.core.data.model.DocumentDetails

@Composable
fun ScanStatusText(parsedQr: DocumentDetails?) {
    val (text, color) = when(parsedQr) {
        null -> "QR код документа пока не найден" to Color.Red
        else -> "QR код документа найден!\nККС-код: ${parsedQr.kksCode}" to Color(0xFF2E7D32)
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = color
    )
}