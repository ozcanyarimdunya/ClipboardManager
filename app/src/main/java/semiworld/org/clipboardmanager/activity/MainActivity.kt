package semiworld.org.clipboardmanager.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import semiworld.org.clipboardmanager.R
import semiworld.org.clipboardmanager.service.ClipboardService

class MainActivity : AppCompatActivity() {
    private val mTAG = "LOGGING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val intent = Intent(this, ClipboardService::class.java)
        startService(intent)

        Log.d(mTAG, "MainActivity:onCreate")
    }
}
