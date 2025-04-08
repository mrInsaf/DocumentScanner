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
import com.mrinsaf.core.data.dataSource.api.DocumentApiService
import com.mrinsaf.core.data.dataSource.api.RetrofitClient
import com.mrinsaf.core.data.repository.DocumentRepositoryImpl
import com.mrinsaf.core.domain.model.QrDocumentDetails
import com.mrinsaf.core.domain.model.QrDocumentDetailsNavType
import com.mrinsaf.core.domain.model.ScreenDestination
import com.mrinsaf.documentscanner.ui.theme.DocumentScannerTheme
import com.mrinsaf.feature_document_details.ui.screens.DocumentDetailsScreen
import com.mrinsaf.feature_scanner.ui.screens.ScannerScreen
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitClient.documentApiService
        val repository = DocumentRepositoryImpl(apiService)

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
                startDestination = ScreenDestination.Scanner,
                navController = navController,
            ) {
                composable<ScreenDestination.Scanner> {
                    ScannerScreen(
                        onShowDocumentDetailsClick = { documentDetails ->
                            navController.navigate(
                                ScreenDestination.DocumentDetailsDestination(
                                    documentDetails
                                )
                            )
                        }
                    )
                }

                composable<ScreenDestination.DocumentDetailsDestination>(
                    typeMap = mapOf(
                        typeOf<QrDocumentDetails>() to QrDocumentDetailsNavType
                    )
                ) { backStackEntry ->
                    val args = backStackEntry.toRoute<ScreenDestination.DocumentDetailsDestination>()
                    val data = args.data

                    DocumentDetailsScreen(
                        personCode = data.personCode,
                        kksCode = data.kksCode,
                        workType = data.workTypeDescription,
                        docType = data.docTypeDescription,
                        versionPrefix = data.versionPrefix,
                        version = data.version,
                        dateInput = data.dateInput,
                        dateCreate = data.dateCreate
                    )
                }

                composable<ScreenDestination.DocumentReader> {

                }
            }
        }
    }
}


