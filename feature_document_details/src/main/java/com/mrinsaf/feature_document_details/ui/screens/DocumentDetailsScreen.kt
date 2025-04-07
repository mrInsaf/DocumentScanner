package com.mrinsaf.feature_document_details.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.ui.components.MainButton
import com.mrinsaf.core.ui.screens.BasicScreen
import com.mrinsaf.feature_document_details.ui.components.DocumentDetailItem

@Composable
fun DocumentDetailsScreen(
    senderCode: String,
    kksCode: String,
    workType: String,
    documentType: String,
    documentVersion: String,
    uploadDate: String,
) {
    BasicScreen(
        title = "Информация о документе"
    ) {
        Spacer(Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DocumentDetailItem(title = "Код отправителя", value = senderCode)

            DocumentDetailItem(title = "ККС Код", value = kksCode)

            DocumentDetailItem(title = "Тип работы", value = workType)

            DocumentDetailItem(title = "Тип документа", value = documentType)

            DocumentDetailItem(title = "Версия документа", value = documentVersion)

            DocumentDetailItem(title = "Дата загрузки", value = uploadDate)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                MainButton(
                    text = "ПРОСМОТР ДОКУМЕНТА",
                    onClick = {println("yo")}
                )
            }
        }
    }
}

