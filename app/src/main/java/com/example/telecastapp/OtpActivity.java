package com.example.telecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

/**
 * This Activity is used for handling user-login related information.
 */
public class OtpActivity extends AppCompatActivity {

    /**
     * This variable is used for storing OTP received.
     */
    String otp_received;

    /**
     * This variable is used for storing OTP entered by the user.
     */
    String otp_entered;

    /**
     * This variable is used for storing phone number.
     */
    private TextView device_no;

    /**
     * This button is used for handling sign-up operation.
     */
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        device_no = findViewById(R.id.text_mobile_no);
        device_no.setText("+91 " + getIntent().getStringExtra("mobile"));
        otp_received = getIntent().getStringExtra("otp");

        EditText otp1 = findViewById(R.id.edit_otp1);
        EditText otp2 = findViewById(R.id.edit_otp2);
        EditText otp3 = findViewById(R.id.edit_otp3);
        EditText otp4 = findViewById(R.id.edit_otp4);
        EditText otp5 = findViewById(R.id.edit_otp5);
        EditText otp6 = findViewById(R.id.edit_otp6);

        confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(view -> {
            if (!otp1.getText().toString().trim().isEmpty()
                    && !otp2.getText().toString().trim().isEmpty()
                    && !otp3.getText().toString().trim().isEmpty()
                    && !otp4.getText().toString().trim().isEmpty()
                    && !otp5.getText().toString().trim().isEmpty()
                    && !otp6.getText().toString().trim().isEmpty()) {

                otp_entered = otp1.getText().toString() + otp2.getText().toString()
                        + otp3.getText().toString() + otp4.getText().toString()
                        + otp5.getText().toString() + otp6.getText().toString();

                Log.d(TAG, "onCreate:entered-code " + otp_entered);
                Log.d(TAG, "onCreate:received-code " + otp_entered);

                if (otp_entered.equals(otp_received)) {
                    Toast.makeText(getApplicationContext(), "OTP Matched " + otp_received,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "OTP Mismatched " +
                            otp_entered + otp_received, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "All Fields required",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /* Assign the TextViews in the array in the order in which you want to shift focus */
        TextView[] otpTextViews = {otp1, otp2, otp3, otp4, otp5, otp6};

        Log.d(TAG, "onCreate:otp " + otpTextViews);

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