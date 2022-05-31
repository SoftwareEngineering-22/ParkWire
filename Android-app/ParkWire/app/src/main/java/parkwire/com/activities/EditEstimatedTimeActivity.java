package parkwire.com.activities;

import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import parkwire.com.R;

public class EditEstimatedTimeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editestimatedtime);
        Button ignore = (Button) findViewById(R.id.ignoreButton);
        Button edit = (Button) findViewById(R.id.editButton);
        EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        EditText currentDate = (EditText) findViewById(R.id.currentDate);
        EditText currentTime = (EditText) findViewById(R.id.currentTime);
        EditText estimatedDate = (EditText) findViewById(R.id.estimatedDate);
        EditText estimatedTime = (EditText) findViewById(R.id.estimatedTime);
    }
}
