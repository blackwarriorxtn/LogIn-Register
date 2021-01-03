package com.example.LoginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText Email,Password ;
    Button LoginButton ;
    DatabaseHelper db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this) ;
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        LoginButton=findViewById(R.id.login_button);
        LoginButton.setOnClickListener(v -> {
            String EmailString = Email.getText().toString();
            String PasswordString = Password.getText().toString();
            Boolean chkemailpassword =db.CheckMailAndPassword(EmailString,PasswordString);
            if (chkemailpassword){
                Toast.makeText(getApplicationContext(), "Login Successful  ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "Email or password is wrong ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

