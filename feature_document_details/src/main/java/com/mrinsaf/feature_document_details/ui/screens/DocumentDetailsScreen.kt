package com.mrinsaf.feature_document_details.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrinsaf.core.presentation.ui.components.MainButton
import com.mrinsaf.core.presentation.ui.screens.BasicScreen
import com.mrinsaf.feature_document_details.ui.components.DocumentDetailItem

@Composable
fun DocumentDetailsScreen(
    personCode: Int,
    kksCode: String,
    workType: String?,
    docType: String?,
    versionPrefix: String?,
    version: Int?,
    dateInput: String,
    dateCreate: String,
    newVersion: Int?,
    newVersionDateCreate: String?,
    onReviewDocumentClick: () -> Unit,
) {
    BasicScreen(title = "Информация о документе") {
        Spacer(Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DocumentDetailItem(title = "Код отправителя", value = personCode.toString())
            DocumentDetailItem(title = "ККС Код", value = kksCode)
            DocumentDetailItem(title = "Тип работы", value = workType ?: "Не указано")
            DocumentDetailItem(title = "Тип документа", value = docType ?: "Не указано")
            DocumentDetailItem(
                title = "Версия документа",
                value = listOfNotNull(versionPrefix, version).joinToString(".")
            )
            DocumentDetailItem(title = "Дата загрузки", value = dateInput)
            DocumentDetailItem(title = "Дата создания", value = dateCreate)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                MainButton(
                    text = "ПРОСМОТР ДОКУМЕНТА",
                    onClick = { onReviewDocumentClick() }
                )
            }

            newVersion?.let {

                Text(
                    text = "Обнаружена новая версия документа",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                DocumentDetailItem(
                    title = "Версия",
                    value = it.toString()
                )
                DocumentDetailItem(
                    title = "Дата загрузки",
                    value = newVersionDateCreate ?: "Не указано"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MainButton(
                        text = "ПРОСМОТР НОВОГО ДОКУМЕНТА",
                        onClick = { onReviewDocumentClick() }
                    )
                }
            }
        }
    }
}
