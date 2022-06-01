package parkwire.com.activities;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import parkwire.com.R;
import parkwire.com.models.Database;
import parkwire.com.models.Paid;
import parkwire.com.models.Seeking;

public class HomeActivity extends AppCompatActivity {
    // variables
    private TextView settingsBtn;
    private TextView parkingNearMeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        //Database mydb = new Database();
        //mydb.printQuery("SELECT * FROM USERS;");

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
                //startActivity(new Intent(HomeActivity.this, ParkingNearMeActivity.class));
                // Seeking Driver
                Seeking seekingDriver = new Seeking("camelKitrino@gmail.com", "camel", "123456",
                        38.2464816, 21.7372183, 0);

                ArrayList<Paid> parkings = seekingDriver.searchPaid();

                for(int i=0; i<parkings.size(); i++)
                    System.out.println(i+". " + parkings.get(i).introduce());
                    int len = parkings.size()-1;
                    System.out.println("Pick parking from 0 - " + len + ":");
                    // assume he picks 0
                    int choice = 0;
                    Paid myParking = parkings.get(choice);
                    System.out.println("You picked: " + myParking.introduce());
                    // how long to stay
                    int time = seekingDriver.setPaidTime();
                    System.out.print("For 2 hours, ");
                    // calculating cost for willing duration
                    float cost = myParking.calculateCost(time);
                    System.out.println("total cost: " + cost);
                    System.out.println("Proceed to reservation?");
                    System.out.println("1. Yes\n 2.No");

                    // make request

            }
        });

    }
}