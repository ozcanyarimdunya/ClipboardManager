package semiworld.org.clipboardmanager.service

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import semiworld.org.clipboardmanager.data.ClipboardEntity

import semiworld.org.clipboardmanager.utils.ClipboardUtils

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

class ClipboardService : Service() {
    private val mTAG = "LOGGING"

    override fun onCreate() {
        super.onCreate()
        Log.d(mTAG, "ClipboardService:onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(mTAG, "ClipboardService:onStartCommand")
        val manager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        manager.addPrimaryClipChangedListener {

            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val utils = ClipboardUtils(clipboardManager)
            val text = utils.fromClipboard()

            // Save to database
            ClipboardEntity.addToDatabase(text)

            Log.d(mTAG, "ClipboardService:onStartCommand:ClipChangedListener:text:" + text)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
