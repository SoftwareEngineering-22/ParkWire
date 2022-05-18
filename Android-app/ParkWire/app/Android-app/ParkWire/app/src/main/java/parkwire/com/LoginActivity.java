package parkwire.com;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
}