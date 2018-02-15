package semiworld.org.clipboardmanager.service;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import semiworld.org.clipboardmanager.utils.ClipboardUtils;

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

public class ClipboardService extends Service {
    private static final String TAG = "HUAWEI";

    public ClipboardService() {
        Log.d(TAG, "ClipboardService:Constructor");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "ClipboardService:onStartCommand");
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (manager != null) {
            manager.addPrimaryClipChangedListener(() -> {
                ClipboardManager _manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text = new ClipboardUtils(_manager).getFromClipboard();

                // Save to database
                Log.d(TAG, "ClipboardChanged: New text " + text);
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
