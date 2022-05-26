package com.ondev.qrscanner

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ondev.qrscanner.ui.theme.QrScannerModuleTheme
import com.ondev.qrscannermodule.QrActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrScannerModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val qrResult = rememberSaveable { mutableStateOf("") }
    val launchQrReader = rememberLauncherForActivityResult(
        QrActivityResultContract()
    ) { barcoderaw ->
        qrResult.value = barcoderaw
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(qrResult.value)
            Button(onClick = {
                launchQrReader.launch(0)
            }) {
                Text("Start QrReader")
            }
        }

    }
}
class QrActivityResultContract :
    ActivityResultContract<Int, String>() {

    override fun parseResult(
        resultCode: Int, intent: Intent?
    ): String {
        return if (resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra("QR_SCANNER_RESULT") ?: ""
        } else {
            ""
        }
    }

    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, QrActivity::class.java)
    }

}