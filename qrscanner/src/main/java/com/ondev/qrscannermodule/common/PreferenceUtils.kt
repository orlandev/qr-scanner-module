/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ondev.qrscannermodule.common

import android.content.Context
import android.graphics.RectF
import com.google.mlkit.vision.barcode.common.Barcode
import com.ondev.qrscannermodule.R
import com.ondev.qrscannermodule.camera.GraphicOverlay

object PreferenceUtils {


    fun getProgressToMeetBarcodeSizeRequirement(
        overlay: GraphicOverlay,
        barcode: Barcode
    ): Float {
        val reticleBoxWidth = getBarcodeReticleBox(overlay).width()
        val barcodeWidth = overlay.translateX(barcode.boundingBox?.width()?.toFloat() ?: 0f)
        val requiredWidth = reticleBoxWidth * 80 / 100
        return (barcodeWidth / requiredWidth).coerceAtMost(1f)
    }


    fun getBarcodeReticleBox(overlay: GraphicOverlay): RectF {
        val overlayWidth = overlay.width.toFloat()
        val overlayHeight = overlay.height.toFloat()
        val boxWidth = overlay.resources.getDimensionPixelOffset(R.dimen.qr_reticle_size)
        val boxHeight = overlay.resources.getDimensionPixelOffset(R.dimen.qr_reticle_size)
        val cx = overlayWidth / 2
        val cy = overlayHeight / 2
        return RectF(cx - boxWidth / 2, cy - boxHeight / 2, cx + boxWidth / 2, cy + boxHeight / 2)
    }

    fun shouldDelayLoadingBarcodeResult(context: Context): Boolean =
        true


}
