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
import com.mrinsaf.core.data.model.ScreenDestination
import com.mrinsaf.documentscanner.ui.theme.DocumentScannerTheme
import com.mrinsaf.feature_scanner.ui.screens.ScannerScreen

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
                startDestination = ScreenDestination.Scanner,
                navController = navController,
            ) {
                composable<ScreenDestination.Scanner> {
                    ScannerScreen()
                }

//                composable<ScreenDestination.DocumentDetailsDestination> {
//                    val args = it.toRoute<ScreenDestination.DocumentDetailsDestination>()
//
//                    DocumentDetailsScreen(
//                        senderCode = args.data.senderCode,
//                        kksCode = args.data.kksCode,
//                        workType = args.data.workType,
//                        documentType = args.data.documentType,
//                        documentVersion = args.data.documentVersion,
//                        uploadDate = args.data.uploadDate,
//                    )
//                }

                composable<ScreenDestination.DocumentReader> {

                }
            }
        }
    }
}


