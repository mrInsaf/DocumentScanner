package com.mrinsaf.feature_document_reader.ui.screens

@Composable
fun PdfViewerWithAndroidView(pdfBytes: ByteArray) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            com.github.barteksc.pdfviewer.PDFView(ctx, null).apply {
                fromBytes(pdfBytes)
                    .defaultPage(0)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .load()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
