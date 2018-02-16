package semiworld.org.clipboardmanager.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.util.Log

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

class ClipboardUtils(private val clipboard: ClipboardManager) {

    private val mTAG = "LOGGING"

    fun fromClipboard(): String {
        val item = clipboard.primaryClip.getItemAt(0)
        if (clipboard.primaryClipDescription.hasMimeType("*/*")) {
            Log.d(mTAG, "ClipboardUtils:fromClipboard:*/*: ${item.text}")
        }
        return item.text.toString()
    }

    fun copyToClipboard(message: String) {
        val data = ClipData.newPlainText("CM", message)
        clipboard.primaryClip = data
        Log.d(mTAG, "ClipboardUtils:copyToClipboard:$message")
    }
}
