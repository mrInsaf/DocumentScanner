package com.mrinsaf.documentscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrinsaf.documentscanner.ui.theme.DocumentScannerTheme

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
        topBar = { TopBar() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                startDestination = Destination.SCANNER,
                navController = navController,
            ) {
                composable(Destination.SCANNER.route) {

                }

                composable(Destination.DOCUMENT_DETAILS.route) {

                }

                composable(Destination.DOCUMENT_READER.route) {

                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Text(
            text = "ПОИСК ДОКУМЕНТОВ"
        )
    }
}


enum class Destination(val route: String) {
    SCANNER("scanner"),
    DOCUMENT_DETAILS("document_details"),
    DOCUMENT_READER("document_reader"),
}
