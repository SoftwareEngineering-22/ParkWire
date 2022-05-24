package parkwire.com;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.sql.Connection;
import java.sql.Statement;


public class LoginActivity extends AppCompatActivity {

    EditText usr,pass;
    //ProgressDialog progressDialog;
   // ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // log in form fill
    usr = (EditText) findViewById(R.id.editTextTextPersonName);
    pass = (EditText) findViewById(R.id.editTextTextPassword);


        // log in forward
         TextView loginBtn = findViewById(R.id.login_btn);
         loginBtn.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this, HomeActivity.class));
             }
         });

        // Add "already have an account btn listener
        TextView btn = findViewById(R.id.create_acc_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    //getting values
    public void loginValues(View view){
        String username = usr.getText().toString();
        String password = pass.getText().toString();
        background bg= new background(this);
        bg.execute(username,password);
    }
}