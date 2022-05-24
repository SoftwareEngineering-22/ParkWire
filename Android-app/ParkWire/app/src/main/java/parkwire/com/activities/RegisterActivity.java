package parkwire.com.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import parkwire.com.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Add "already have an account btn listener
        TextView btn = findViewById(R.id.have_account_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}