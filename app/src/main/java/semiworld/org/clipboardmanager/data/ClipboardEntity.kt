package semiworld.org.clipboardmanager.data

import android.text.TextUtils

import com.raizlabs.android.dbflow.sql.language.SQLite
import java.util.UUID

import semiworld.org.clipboardmanager.model.ClipboardModel
import semiworld.org.clipboardmanager.model.ClipboardModel_Table

/**
 * Author Ozcan YARIMDUNYA
 * Created at 16.02.2018.
 */

object ClipboardEntity {
    private val TAG = "LOGGING"

    val all: List<ClipboardModel>
        get() = SQLite.select()
                .from<ClipboardModel>(ClipboardModel::class.java!!)
                .orderBy(ClipboardModel_Table.date, false)
                .queryList()

    fun getFirstOne(uuid: UUID): ClipboardModel? {
        return SQLite.select()
                .from<ClipboardModel>(ClipboardModel::class.java!!)
                .where(ClipboardModel_Table.uuid.eq(uuid))
                .querySingle()
    }

    fun getFirstOne(text: String): ClipboardModel? {
        return SQLite.select()
                .from<ClipboardModel>(ClipboardModel::class.java!!)
                .where(ClipboardModel_Table.text.eq(text))
                .querySingle()
    }

    fun getLimited(limit: Int): List<ClipboardModel> {
        return SQLite.select()
                .from<ClipboardModel>(ClipboardModel::class.java!!)
                .orderBy(ClipboardModel_Table.date, false)
                .limit(limit)
                .queryList()
    }

    fun create(text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }
        ClipboardModel(text).insert()
    }

    fun create(model: ClipboardModel?) {
        if (model == null || TextUtils.isEmpty(model.text)) {
            return
        }
        model.insert()
    }

    fun update(oldText: String, newText: String) {
        if (TextUtils.isEmpty(newText)) {
            return
        }
        ClipboardEntity.getFirstOne(oldText)!!.delete()
        ClipboardModel(newText).insert()
    }

    fun update(model: ClipboardModel, text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }
        model.delete()
        ClipboardModel(text).insert()
    }

    fun delete(text: String) {
        ClipboardEntity.getFirstOne(text)!!.delete()
    }

    fun delete(model: ClipboardModel) {
        model.delete()
    }
}
