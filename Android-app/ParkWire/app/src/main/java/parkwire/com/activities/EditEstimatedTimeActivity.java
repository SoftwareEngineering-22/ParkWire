package parkwire.com.activities;

import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;

import parkwire.com.R;
import parkwire.com.models.Parked;
import parkwire.com.models.User;

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
        User u = new User("", "", "");
        Parked p = new Parked(u.getEmail(), u.getUsername(), u.getPassword(), 3, 2, 1, null);

        if (p.calcRemEstimate() < 10 * 60) { // κάτω από 10 λεπτά
            p.Notify();
            if (edit != null) {
                p.setTimeEstimate(p.getTimeEstimate());
            }
        }


    }
}
