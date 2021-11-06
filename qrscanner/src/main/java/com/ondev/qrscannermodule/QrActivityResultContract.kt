package com.ondev.qrscannermodule

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class QrActivityResultContract :
    ActivityResultContract<Int, String>() {

    override fun parseResult(
        resultCode: Int, intent: Intent?
    ): String {
        return if (resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra(EXTRA_STRING) ?: ""
        } else {
            ""
        }
    }

    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, QrActivity::class.java)
    }

}