package semiworld.org.clipboardmanager.data

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */

@Table(database = ClipboardDatabase::class)
class ClipboardModel(@PrimaryKey var uuid: UUID = UUID.randomUUID(),
                     @Column var text: String? = null,
                     @Column var date: Date = Date()) : BaseModel() {
    override fun toString(): String {
        return "ClipboardModel(uuid=$uuid, text='$text', date=$date)"
    }
}