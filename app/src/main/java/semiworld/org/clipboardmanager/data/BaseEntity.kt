package semiworld.org.clipboardmanager.data

/**
 * Author Ozcan YARIMDUNYA
 * Created at 16.02.2018.
 */

interface BaseEntity {

    fun _get(text: String): ClipboardModel?

    fun _getAll(): MutableList<ClipboardModel>

    fun _getInLimit(limit: Int): MutableList<ClipboardModel>

    fun _create(text: String)

    fun _update(model: ClipboardModel, text: String)

    fun _delete(text: String)
}
