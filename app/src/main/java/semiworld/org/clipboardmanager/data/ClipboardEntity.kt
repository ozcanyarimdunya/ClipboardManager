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
        val model = this.getClipboardByText(text)
        if (model != null) {
            if (model.exists()) {
                this.updateClipboard(model, text)
            }
        } else {
            this.createClipboard(text)
        }
        Log.d(mTAG, "DB:model:$model")
    }

    override fun getClipboardByText(text: String): ClipboardModel? {
        return Select()
                .from<ClipboardModel>(ClipboardModel::class.java)
                .where(ClipboardModel_Table.text.eq(text))
                .querySingle()
    }

    override fun getClipboardList(): MutableList<ClipboardModel> {
        return Select()
                .from(ClipboardModel::class.java)
                .orderBy(ClipboardModel_Table.date, false)
                .queryList()
    }

    override fun getClipboardListWithLimit(limit: Int): MutableList<ClipboardModel> {
        return Select()
                .from<ClipboardModel>(ClipboardModel::class.java)
                .orderBy(ClipboardModel_Table.date, false)
                .limit(limit)
                .queryList()
    }

    override fun createClipboard(text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }
        val model = ClipboardModel(text = text)
        model.insert()
    }

    override fun updateClipboard(model: ClipboardModel, text: String) {
        if (TextUtils.isEmpty(text)) {
            return
        }

        model.text = text
        model.date = Date()
        model.update()
    }

    override fun deleteClipboard(text: String) {
        val model = this.getClipboardByText(text)
        model?.delete()
    }
}
