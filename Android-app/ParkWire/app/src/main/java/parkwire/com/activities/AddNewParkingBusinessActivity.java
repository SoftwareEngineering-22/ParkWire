package parkwire.com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import parkwire.com.R;

import parkwire.com.models.Paid;
import parkwire.com.models.User;
import parkwire.com.models.Valet;

public class AddNewParkingBusinessActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editestimatedtime);
        Button addNewBusiness = (Button) findViewById(R.id.addNewBusiness);
        Button submit = (Button) findViewById(R.id.submit);
        TextView lat = (TextView) findViewById(R.id.lat);
        TextView lon = (TextView) findViewById(R.id.lon);
        TextView cost = (TextView) findViewById(R.id.cost);
        TextView capacity = (TextView) findViewById(R.id.capacity);
        TextView contact_info = (TextView) findViewById(R.id.contact_info);
        TextView work_hours = (TextView) findViewById(R.id.work_hours);
        TextView address = (TextView) findViewById(R.id.address);

        float flat = new Float(String.valueOf(lat));
        float flon = new Float(String.valueOf(lon));
        int icost = new Integer(String.valueOf(cost));
        int icapacity = new Integer(String.valueOf(capacity));
        String scontact_info = String.valueOf(contact_info);
        String swork_hours = String.valueOf(work_hours);
        String saddress = String.valueOf(address);

        User u = new User("", "", "");
        Valet v = new Valet(u.getEmail(), u.getUsername(), u.getPassword(), "", "");

        if (addNewBusiness != null && submit != null) {
            v.addNewBusiness(flat, flon, icapacity, icost, scontact_info, swork_hours, saddress);
        }

    }
}
