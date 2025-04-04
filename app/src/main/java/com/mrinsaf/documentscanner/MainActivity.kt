package com.mrinsaf.documentscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrinsaf.documentscanner.ui.theme.DocumentScannerTheme
import com.mrinsaf.feature_scanner.ui.screens.ScannerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocumentScannerTheme {
                DocumentScannerApp()
            }
        }
    }
}

@Composable
fun DocumentScannerApp() {
    val navController = rememberNavController()

    Scaffold(
//        topBar = { TopBar() },
    )    {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                startDestination = Destination.SCANNER.route,
                navController = navController,
            ) {
                composable(Destination.SCANNER.route) {
                    ScannerScreen()
                }

                composable(Destination.DOCUMENT_DETAILS.route) {

                }

                composable(Destination.DOCUMENT_READER.route) {

                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar() {
//    TopAppBar(
//        title = {
//            Text(
//                text = "ПОИСК ДОКУМЕНТОВ",
//        )},
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.secondary
//        )
//    )
//}

enum class Destination(val route: String) {
    SCANNER("scanner"),
    DOCUMENT_DETAILS("document_details"),
    DOCUMENT_READER("document_reader"),
}
