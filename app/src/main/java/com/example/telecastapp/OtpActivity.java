package com.example.telecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * This Activity is used for handling user-login related information.
 */
public class OtpActivity extends AppCompatActivity {

    /**
     * This Variable is used for storing phone number.
     */
    private TextView cell_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        cell_no = (TextView) findViewById(R.id.text_mobile_no);
        cell_no.setText("+91 "+getIntent().getStringExtra("mobile"));
    }
}