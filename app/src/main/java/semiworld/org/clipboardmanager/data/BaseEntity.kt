package semiworld.org.clipboardmanager.data

/**
 * Author Ozcan YARIMDUNYA
 * Created at 16.02.2018.
 */

interface BaseEntity {

    fun getClipboardByText(text: String): ClipboardModel?

    fun getClipboardList(): MutableList<ClipboardModel>

    fun getClipboardListWithLimit(limit: Int): MutableList<ClipboardModel>

    fun createClipboard(text: String)

    fun updateClipboard(model: ClipboardModel, text: String)

    fun deleteClipboard(text: String)
}
