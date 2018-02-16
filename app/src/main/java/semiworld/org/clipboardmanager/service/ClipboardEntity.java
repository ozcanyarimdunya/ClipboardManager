package semiworld.org.clipboardmanager.service;

import android.text.TextUtils;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;
import java.util.UUID;

import semiworld.org.clipboardmanager.model.ClipboardModel;
import semiworld.org.clipboardmanager.model.ClipboardModel_Table;

/**
 * Author Ozcan YARIMDUNYA
 * Created at 16.02.2018.
 */

public class ClipboardEntity {
    private static final String TAG = "LOGGING";

    public static ClipboardModel getFirstOne(UUID uuid) {
        return SQLite.select()
                .from(ClipboardModel.class)
                .where(ClipboardModel_Table.uuid.eq(uuid))
                .querySingle();
    }

    public static ClipboardModel getFirstOne(String text) {
        return SQLite.select()
                .from(ClipboardModel.class)
                .where(ClipboardModel_Table.text.eq(text))
                .querySingle();
    }

    public static List<ClipboardModel> getAll() {
        return SQLite.select()
                .from(ClipboardModel.class)
                .orderBy(ClipboardModel_Table.date, false)
                .queryList();
    }

    public static List<ClipboardModel> getLimited(int limit) {
        return SQLite.select()
                .from(ClipboardModel.class)
                .orderBy(ClipboardModel_Table.date, false)
                .limit(limit)
                .queryList();
    }

    public static void create(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        new ClipboardModel(text).insert();
    }

    public static void create(ClipboardModel model) {
        if (model == null || TextUtils.isEmpty(model.getText())) {
            return;
        }
        model.insert();
    }

    public static void update(String oldText, String newText) {
        if (TextUtils.isEmpty(newText)) {
            return;
        }
        ClipboardEntity.getFirstOne(oldText).delete();
        new ClipboardModel(newText).insert();
    }

    public static void update(ClipboardModel model, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        model.delete();
        new ClipboardModel(text).insert();
    }

    public static void delete(String text) {
        ClipboardEntity.getFirstOne(text).delete();
    }

    public static void delete(ClipboardModel model) {
        model.delete();
    }
}
