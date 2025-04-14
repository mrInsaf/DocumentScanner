package com.mrinsaf.feature_document_reader.ui.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import androidx.core.graphics.createBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DocumentReaderViewModel : ViewModel() {
    private val _pages = MutableStateFlow<List<ImageBitmap>>(emptyList())
    val pages: StateFlow<List<ImageBitmap>> get() = _pages

    fun renderPdf(context: Context, pdfBytes: ByteArray) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                println("DEBUG: Starting PDF rendering process")

                // Создаем временный файл
                val tempFile = File.createTempFile("temp_pdf", ".pdf", context.cacheDir).apply {
                    writeBytes(pdfBytes)
                    println("DEBUG: Temporary file created at ${this.absolutePath}")
                }

                // Открываем PDF файл через ParcelFileDescriptor
                ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY).use { pfd ->
                    println("DEBUG: Successfully opened ParcelFileDescriptor")

                    PdfRenderer(pfd).use { renderer ->
                        println("DEBUG: PDF Renderer initialized. Page count: ${renderer.pageCount}")

                        val newPages = mutableListOf<ImageBitmap>()
                        for (index in 0 until renderer.pageCount) {
                            println("DEBUG: Rendering page $index")
                            renderer.openPage(index).use { page ->
                                println("DEBUG: Opened page $index. Dimensions: ${page.width}x${page.height}")

                                // Масштабируем под ширину экрана
                                val scale = context.resources.displayMetrics.widthPixels.toFloat() / page.width
                                println("DEBUG: Calculated scale factor: $scale")

                                val bitmap = Bitmap.createBitmap(
                                    (page.width * scale).toInt(),
                                    (page.height * scale).toInt(),
                                    Bitmap.Config.ARGB_8888
                                )
                                println("DEBUG: Created bitmap with dimensions: ${bitmap.width}x${bitmap.height}")

                                // Рендерим страницу в битмап
                                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                                println("DEBUG: Rendered page $index into bitmap")

                                // Преобразуем битмап в ImageBitmap и добавляем в список
                                newPages.add(bitmap.asImageBitmap())
                                println("DEBUG: Added page $index to the list of rendered pages")
                            }
                        }

                        // Обновляем состояние списка страниц
                        _pages.update { newPages }
                        println("DEBUG: Updated _pages state with ${newPages.size} pages")
                    }
                }

                // Удаляем временный файл
                tempFile.delete()
                println("DEBUG: Temporary file deleted")
            } catch (e: Exception) {
                println("DEBUG: Error occurred during PDF rendering: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}