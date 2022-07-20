package com.example.telecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

/**
 * This Activity is used for handling login related operation.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * This Variable is used to handle user login information.
     */
    Button button_otp;
    /**
     * This Variable is used to store/handle user signup information.
     */
    Button signUp;
    /**
     * This Variable is used to store/handle user login information.
     */
    EditText userNo;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_otp = findViewById(R.id.btn_otp);
        userNo = findViewById(R.id.edit_phone_no);
        progressBar = findViewById(R.id.SHOW_PROGRESS);

        button_otp.setOnClickListener(view -> {
            if (!userNo.getText().toString().trim().isEmpty()) {
                if (userNo.getText().toString().trim().length() >= 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    button_otp.setVisibility(View.INVISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + userNo,         // Phone number to verify
                            60,               // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            LoginActivity.this,// Activity (for callback binding)
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(getApplicationContext(), "Verification Complete", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong while sending otp", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onCreate:verify "+e.getMessage());
                                    progressBar.setVisibility(View.INVISIBLE);
                                    button_otp.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onCodeSent(@NonNull String codeActivated, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(codeActivated, forceResendingToken);
                                    navigateToOtpActivity(view, codeActivated);
                                }
                            });
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
    public void navigateToOtpActivity(View view, String codeActivated) {
        Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
        intent.putExtra("mobile", userNo.getText().toString());
        intent.putExtra("otp", codeActivated);
        startActivity(intent);
    }
}
