package com.example.telecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity is used for handling login related operation.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * This Variable is used to handle user login information.
     */
    Button otp;
    /**
     * This Variable is used to store/handle user signup information.
     */
    Button signUp;
    /**
     * This Variable is used to store/handle user login information.
     */
    EditText userNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        otp = findViewById(R.id.btn_otp);
        userNo = findViewById(R.id.edit_phone_no);

        otp.setOnClickListener(view -> {
            if (!userNo.getText().toString().trim().isEmpty()) {
                if(userNo.getText().toString().trim().length() == 10) {
                    navigateToOtpActivity(view);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Phone Number",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "All Fields are Required",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This Method is used for handling navigation to OTP activity.
     */
    public void navigateToOtpActivity(View view){
        Intent intent = new Intent(getApplicationContext(),OtpActivity.class);
        intent.putExtra("mobile",userNo.getText().toString());
        startActivity(intent);
    }
}
