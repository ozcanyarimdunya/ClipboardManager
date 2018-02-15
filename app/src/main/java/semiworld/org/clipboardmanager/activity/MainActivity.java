package semiworld.org.clipboardmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;

import semiworld.org.clipboardmanager.R;
import semiworld.org.clipboardmanager.model.ClipboardModel;
import semiworld.org.clipboardmanager.model.ClipboardModel_Table;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HUAWEI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((View view) -> {
            Log.d(TAG, "Service working");
//            startService(new Intent(getApplicationContext(), ClipboardService.class));

            // Insert
            ClipboardModel model0 = new ClipboardModel("Hello World");
            model0.insert();
            Log.d(TAG, "ClipboardInsert: " + model0.toString());

            // Select single
            ClipboardModel model1 = SQLite.select()
                    .from(ClipboardModel.class)
                    .where(ClipboardModel_Table.text.eq("Hello World"))
                    .querySingle();
            if (model1 != null) {
                Log.d(TAG, "ClipboardSelect: " + model1.toString());
                model1.setDate(new Date());
                model1.update();
            }

            // Update
            ClipboardModel model2 = SQLite.select()
                    .from(ClipboardModel.class)
                    .where(ClipboardModel_Table.text.eq("Hello World"))
                    .querySingle();
            if (model2 != null) {
                Log.d(TAG, "ClipboardModelUpdated: " + model2.toString());
            }

            // Delete
            ClipboardModel model3 = SQLite.select()
                    .from(ClipboardModel.class)
                    .where(ClipboardModel_Table.text.eq("Hello World"))
                    .querySingle();
            if (model3 != null) {
                Log.d(TAG, "ClipboardModelDeleted: " + model3.toString());
                model3.delete();
            }
        });
    }
}
