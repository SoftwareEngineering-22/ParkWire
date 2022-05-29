package parkwire.com.activities;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import parkwire.com.R;
import parkwire.com.models.Database;


public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // log in forward
        Button loginBtn = (Button) findViewById(R.id.login_btn);
        // log in form fill
        EditText usr = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText pass = (EditText) findViewById(R.id.editTextTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v) {
                 String username = usr.getText().toString();
                 String password = pass.getText().toString();
                 if(userExists(username, password))
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                 else
                     System.out.println("Get outta here");
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

    private Boolean userExists(String username, String password){
        Connection con = new Database().connect();
        String q = "SELECT COUNT(*) AS cnt FROM USERS WHERE username = ? and password = ?";
        // Giorgakhs, 123456789
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if (rs.getInt("cnt") == 1) return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}