package parkwire.com.activities;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.*;

import parkwire.com.R;
import parkwire.com.models.Database;

public class HomeActivity extends AppCompatActivity {
    // variables
    private TextView settingsBtn;
    private TextView parkingNearMeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        Database mydb = new Database();
        mydb.printQuery("SELECT * FROM USERS;");

        // settings activity forward
        settingsBtn = findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        parkingNearMeBtn = findViewById(R.id.near_me_btn);
        parkingNearMeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ParkingNearMeActivity.class));
            }
        });

    }
}