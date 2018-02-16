package semiworld.org.clipboardmanager.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.util.Log;

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

public class ClipboardUtils {
    private static final String TAG = "LOGGING";
    private ClipboardManager clipboard;

    public ClipboardUtils(ClipboardManager clipboard) {
        this.clipboard = clipboard;
//        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void copyToClipboard(String message) {
        ClipData data = ClipData.newPlainText("CM", message);
        clipboard.setPrimaryClip(data);
        Log.d(TAG, "<" + message + "> copied to clipboard");
    }

    public String getFromClipboard() {
        // Check if it is text
        if (!clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
            return null;

        if (clipboard != null) {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String clipboardText = String.valueOf(item.getText().toString());
            Log.d(TAG, "<" + clipboardText + "> comes from clipboard");

            return clipboardText;
        }

        return null;
    }
}
