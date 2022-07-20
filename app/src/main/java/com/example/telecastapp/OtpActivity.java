package com.example.telecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * This Activity is used for handling user-login related information.
 */
public class OtpActivity extends AppCompatActivity {

    /**
     * This Variable is used for storing phone number.
     */
    private TextView cell_no;
    private Button confirm;
    String code_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        cell_no = (TextView) findViewById(R.id.text_mobile_no);
        cell_no.setText("+91 "+getIntent().getStringExtra("mobile"));
        code_received = getIntent().getStringExtra("otp");

        TextView otp1 = (TextView) findViewById(R.id.edit_otp1);
        TextView otp2 = (TextView) findViewById(R.id.edit_otp2);
        TextView otp3 = (TextView) findViewById(R.id.edit_otp3);
        TextView otp4 = (TextView) findViewById(R.id.edit_otp4);
        TextView otp5 = (TextView) findViewById(R.id.edit_otp5);
        TextView otp6 = (TextView) findViewById(R.id.edit_otp6);

        /* Assign the TextViews in the array in the order in which you want to shift focus */
        TextView[] otpTextViews = {otp1, otp2, otp3, otp4, otp5, otp6};

        confirm = (Button) findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(view -> {
            if (otpTextViews!= null) {
                Toast.makeText(getApplicationContext(), "otp check"+code_received,
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "All Fields required",
                        Toast.LENGTH_SHORT).show();
            }

        });

        Log.d(TAG, "onCreate:otp "+otpTextViews);

        /*
            This method is used to handle edit text move to next after enter the digits
         */
        for (TextView currTextView : otpTextViews) {
            currTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    nextTextView().requestFocus();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }

                public TextView nextTextView() {
                    int i;
                    for (i = 0; i < otpTextViews.length - 1; i++) {
                        if (otpTextViews[i] == currTextView)
                            return otpTextViews[i + 1];
                    }
                    return otpTextViews[i];
                }
            });
        }
    }
}