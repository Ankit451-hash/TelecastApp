package com.example.telecastapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// *  This Activity is used for handling login related activity.
public class LoginActivity extends AppCompatActivity {

    // *  This Variable is used to store/handle user login information.
    Button logIn, signUp;
    EditText userNo, userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        logIn = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.btn_signup);
        userNo = findViewById(R.id.edit_phone_no);
        userPwd = findViewById(R.id.edit_pwd);

        String phoneNo = userNo.getText().toString();
        String pwd = userNo.getText().toString();

        // *  This method is used to handle user login click.
        logIn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(phoneNo) || TextUtils.isEmpty(pwd)) {
                Toast t = Toast.makeText(getApplicationContext(), "All Fields Required",
                        Toast.LENGTH_SHORT);
                t.show();
            } else {
                Toast.makeText(getApplicationContext(), "Button click",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // *  This method is used to handle new user creation operation.
        signUp.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Create an Account", Toast.LENGTH_SHORT)
                    .show();
        });
    }
}