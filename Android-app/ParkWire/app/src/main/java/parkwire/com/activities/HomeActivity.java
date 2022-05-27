package parkwire.com.activities;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.sql.*;

import parkwire.com.R;

public class HomeActivity extends AppCompatActivity {
    private Connection connection = null;
    private final String host = "ec2-34-247-172-149.eu-west-1.compute.amazonaws.com";
    private final String database = "d20nd037ds0s85";
    private final int port = 5432;
    private final String user = "qtuiehpetqjxoe";
    private final String pass = "fb47d2dabf861efc95b5a1d760e0a0026d0d88dc5ae79bd87188da1cf8992840";
    private String url = "jdbc:postgresql://%s:%d/%s?sslmode=require&rejectUnauthorized=false";
    // variables
    private TextView settingsBtn;
    private TextView parkingNearMeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.url = String.format(this.url, this.host, this.port, this.database);
        // example for db

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            String SQL = "SELECT * FROM users";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(SQL);
            int i = 1;
            while(rs.next()){
                while(i < 7) {
                    System.out.print(rs.getString(i) + "    ");
                    i++;
                }
                System.out.println();
                i = 1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

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