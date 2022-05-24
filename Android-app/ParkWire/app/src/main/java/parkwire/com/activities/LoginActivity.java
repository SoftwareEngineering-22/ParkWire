package parkwire.com.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import parkwire.com.ui.Background;
import parkwire.com.R;

public class LoginActivity extends AppCompatActivity {

    EditText usr,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr= (EditText) findViewById(R.id.editTextTextPersonName);
        pass= (EditText) findViewById(R.id.editTextTextPassword);

        // log in forward
         /*TextView loginBtn = findViewById(R.id.login_btn);
         loginBtn.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v) {
                 startActivity(new Intent(LoginActivity.this, HomeActivity.class));
             }
         });*/



        // Add "already have an account btn listener
        TextView btn = findViewById(R.id.create_acc_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void loginButton(View view){
        String username = usr.getText().toString();
        String password = pass.getText().toString();

        Background bg= new Background(this);
        bg.execute(username,password);
    }
}