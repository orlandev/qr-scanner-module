# qr-scanner-module
At #Inmersoft we have used the Google MLKit library several times to read QR codes, it is very powerful and versatile. After several projects reintegrating this library, I decided to isolate it in a separate module that would allow me a better integration. It 

# Add Jitpack.io 
maven { url 'https://jitpack.io' }

# Add dependecie
implementation 'com.github.orlando-dev-code:qr-scanner-module:v1.0.1'

# See the sample
Sample code: https://github.com/orlando-dev-code/qr-scanner-module/tree/master/app

# Sample

<code>
  
        @Composable
        fun App() {
          val qrResult = rememberSaveable { mutableStateOf("") }
          val launchQrReader = rememberLauncherForActivityResult(QrActivityResultContract()) { barcoderaw ->
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
</code>

