package parkwire.com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import parkwire.com.R;
import parkwire.com.models.User;
import parkwire.com.models.Valet;

public class EditPaidParkingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editestimatedtime);
        Button submit = (Button) findViewById(R.id.submit);
        TextView lat = (TextView) findViewById(R.id.lat);
        TextView lon = (TextView) findViewById(R.id.lon);
        TextView cost = (TextView) findViewById(R.id.cost);
        TextView capacity = (TextView) findViewById(R.id.capacity);
        TextView contact_info = (TextView) findViewById(R.id.contact_info);
        TextView work_hours = (TextView) findViewById(R.id.work_hours);
        TextView address = (TextView) findViewById(R.id.address);
        TextView id = (TextView) findViewById(R.id.id);
        Button edit_business = (Button) findViewById(R.id.edit_business);

        float flat = new Float(String.valueOf(lat));
        float flon = new Float(String.valueOf(lon));
        int icost = new Integer(String.valueOf(cost));
        int icapacity = new Integer(String.valueOf(capacity));
        String scontact_info = String.valueOf(contact_info);
        String swork_hours = String.valueOf(work_hours);
        String saddress = String.valueOf(address);
        int iid = new Integer(String.valueOf(id));

        User u = new User("", "", "");
        Valet v = new Valet(u.getEmail(), u.getUsername(), u.getPassword(), "", "");

        if (edit_business != null && submit != null) {
            v.editBusiness(flat, flon, icapacity, icost, scontact_info, swork_hours, saddress, iid);
        }

    }
}
