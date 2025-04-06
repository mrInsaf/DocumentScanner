package com.mrinsaf.documentscanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mrinsaf.documentscanner.ui.theme.DocumentScannerTheme
import com.mrinsaf.feature_document_details.ui.screens.DocumentDetailsScreen
import com.mrinsaf.feature_scanner.ui.screens.ScannerScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), 0
            )
        }
        else {
            println("Norm")
        }


        enableEdgeToEdge()
        setContent {
            DocumentScannerTheme {
                DocumentScannerApp()
            }
        }
    }

    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }


}

@Composable
fun DocumentScannerApp(
) {
    val navController = rememberNavController()

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                startDestination = Screens.Scanner,
                navController = navController,
            ) {
                composable<Screens.Scanner> {
                    ScannerScreen {
                        println("data is: $it")
                    }
                }

                composable<Screens.DocumentDetails> {
                    val args = it.toRoute<Screens.DocumentDetails>()

                    DocumentDetailsScreen(
                        senderCode = args.senderCode,
                        kksCode = args.kksCode,
                        workType = args.workType,
                        documentType = args.documentType,
                        documentVersion = args.documentVersion,
                        uploadDate = args.uploadDate,
                    )
                }

                composable<Screens.DocumentReader> {

                }
            }
        }
    }
}


//enum class Destination(val route: String) {
//    SCANNER("scanner"),
//    DOCUMENT_DETAILS("document_details"),
//    DOCUMENT_READER("document_reader"),
//}


sealed class Screens() {
    @Serializable
    object Scanner: Screens()

    @Serializable
    data class DocumentDetails(
        val senderCode: String,
        val kksCode: String,
        val workType: String,
        val documentType: String,
        val documentVersion: String,
        val uploadDate: String,
    )

    @Serializable
    data class DocumentReader(val documentTitle: String)
}
