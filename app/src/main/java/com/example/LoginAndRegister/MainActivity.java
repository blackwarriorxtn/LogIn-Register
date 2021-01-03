package com.example.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db ;
    EditText EMail, Password, PassConfirmation;
    Button register ,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new DatabaseHelper(this ) ;
        EMail = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        PassConfirmation = findViewById(R.id.confirm_password);
        register = findViewById(R.id.signup_button);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,Login.class) ;
            startActivity(i);

        });
        register.setOnClickListener(v -> {
            String s1 = EMail.getText().toString();
            String s2= Password.getText().toString();
            String s3= PassConfirmation.getText().toString();
            if (s1.equals("") || s2.equals("") || s3.equals("")) {
                Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
            } else {
                if (s2.equals(s3)) {
                    boolean chkemail = db.CheckEmail(s2);
                    if (chkemail) {
                        boolean insert = db.InsetData(s1, s2);
                        if (insert) {
                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_LONG).show();
                    }
                }
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();

            }
        });


    }
}