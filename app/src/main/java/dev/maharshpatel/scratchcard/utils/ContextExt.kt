package dev.maharshpatel.scratchcard.utils

import android.content.Context

fun Context.dp(dp: Float): Int {
    return (dp * resources.displayMetrics.density).toInt()
}