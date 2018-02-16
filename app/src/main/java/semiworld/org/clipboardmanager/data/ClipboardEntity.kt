package semiworld.org.clipboardmanager.data

import android.text.TextUtils
import android.util.Log
import com.raizlabs.android.dbflow.sql.language.Select
import java.util.*


/**
 * Author Ozcan YARIMDUNYA
 * Created at 16.02.2018.
 */

object ClipboardEntity : BaseEntity {
    private const val mTAG = "LOGGING"

    fun addToDatabase(text: String) {
        val model = this._get(text)
        if (model != null) {
            if (model.exists()) {
                this._update(model, text)
            }
        } else {
            this._create(text)
        }
        Log.d(mTAG, "DB:model:$model")
    }

    override fun _get(text: String): ClipboardModel? {
        return Select()
                .from<ClipboardModel>(ClipboardModel::class.java)
                .where(ClipboardModel_Table.text.eq(text))
                .querySingle()
    }

    override fun _getAll(): MutableList<ClipboardModel> {
        return Select()
                .from(ClipboardModel::class.java)
                .orderBy(ClipboardModel_Table.date, false)
                .queryList()
    }

    override fun _getInLimit(limit: Int): MutableList<ClipboardModel> {
        return Select()
                .from<ClipboardModel>(ClipboardModel::class.java)
                .orderBy(ClipboardModel_Table.date, false)
                .limit(limit)
                .queryList()
    }

    override fun _create(text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }
        val model = ClipboardModel(text = text)
        model.insert()
    }

    override fun _update(model: ClipboardModel, text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }

        model.text = text
        model.date = Date()
        model.update()
    }

    override fun _delete(text: String) {
        val model = this._get(text)
        model?.delete()
    }
}
