package parkwire.com.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import parkwire.com.R;
import parkwire.com.models.Paid;
import parkwire.com.models.Seeking;
import parkwire.com.models.User;

public class SettingsActivity extends AppCompatActivity {
    // assume user info have been passed through activity
    private User user = new User("camel@gmail.com", "camel_kitrino", "123456");
    private Button userBtn, passBtn, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userBtn = (Button) findViewById(R.id.userBtn);
        passBtn = (Button) findViewById(R.id.passBtn);
        history = (Button) findViewById(R.id.pay_history);


        history.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
               user.viewHistory();
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                user.changeUsername();
            }
        });

        passBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                user.changePassword();
            }
        });
    }
}