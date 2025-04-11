package com.mrinsaf.core.domain.model

import com.mrinsaf.core.data.repository.local.DocWorkTypeRepository
import kotlinx.serialization.Serializable

@Serializable
data class QrDocumentDetails(
    val personCode: Int,
    val kksCode: String,
    val workType: String? = null,
    val docType: String? = null,
    val versionPrefix: String,
    val version: Int,
    val dateInput: String,
    val dateCreate: String
) {
    // Вычисляемые свойства для расшифровки
    val workTypeDescription: String?
        get() = workType?.let { DocWorkTypeRepository.getWorkType(it).also { println(it) }  ?.description }

    val docTypeDescription: String?
        get() = docType?.let { DocWorkTypeRepository.getDocType(it)?.description }

    override fun toString(): String {
        return "DocumentDetails(personCode=$personCode, kksCode='$kksCode', workType=$workType, docType=$docType, versionPrefix=$versionPrefix, version=$version, dateInput='$dateInput', dateCreate='$dateCreate', workTypeDescription=$workTypeDescription, docTypeDescription=$docTypeDescription)"
    }
}