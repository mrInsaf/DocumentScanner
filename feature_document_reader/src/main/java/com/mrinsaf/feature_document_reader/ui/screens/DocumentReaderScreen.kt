import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrinsaf.feature_document_reader.ui.viewModel.DocumentReaderViewModel


@Composable
fun DocumentReaderScreen(
    pdfBytes: ByteArray,
    context: Context,
    viewModel: DocumentReaderViewModel
) {
    val pages = viewModel.pages.collectAsStateWithLifecycle(emptyList())

    LaunchedEffect(Unit) {
        viewModel.renderPdf(context, pdfBytes)
    }

    LazyColumn {
        // Обратите внимание на .value здесь
        items(pages.value) { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = "PDF Page",
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}