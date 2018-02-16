package semiworld.org.clipboardmanager.model;

import android.text.TextUtils;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import semiworld.org.clipboardmanager.data.ClipboardDatabase;

/**
 * Author Ozcan YARIMDUNYA
 * Created at 15.02.2018.
 */
//
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(database = ClipboardDatabase.class)
public class ClipboardModel extends BaseModel {
    @PrimaryKey
    private UUID uuid = UUID.randomUUID();

    @NonNull
    @Column
    private String text;

    @Column
    private Date date = new Date();

    @Override
    public String toString() {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(String.valueOf(date)))
            return "<ClipboardModel(text=, date=)>";
        return "<ClipboardModel(text=" + text + ", date=" + date + ")>";
    }
}