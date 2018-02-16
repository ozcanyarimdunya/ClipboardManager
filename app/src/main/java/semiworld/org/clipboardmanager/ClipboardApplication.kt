package semiworld.org.clipboardmanager

import android.app.Application

import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager

import semiworld.org.clipboardmanager.data.ClipboardDatabase

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

class ClipboardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(ClipboardDatabase::class.java)
                        .databaseName("ClipboardDatabase")
                        .build())
                .build())
    }
}
