package com.example.telecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity is used for handling login related operation.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * This variable is used to handle user login information.
     */
    Button button_otp;
    /**
     * This variable is used to store/handle user signup information.
     */
    Button signUp;
    /**
     * This variable is used to store/handle user login information.
     */
    EditText userNo;

    ProgressBar progressBar;

    FirebaseAuth auth;

    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_otp = findViewById(R.id.btn_otp);
        userNo = findViewById(R.id.edit_phone_no);
        progressBar = findViewById(R.id.SHOW_PROGRESS);

        auth = FirebaseAuth.getInstance();

        button_otp.setOnClickListener(view -> {
            if (!userNo.getText().toString().trim().isEmpty()) {
                if (userNo.getText().toString().trim().length() >= 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    button_otp.setVisibility(View.INVISIBLE);
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(userNo.getText().toString())
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(LoginActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                }

                                @Override
                                public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verifyId, forceResendingToken);
                                    verificationId = verifyId;
                                }
                            }).build();

                    PhoneAuthProvider.verifyPhoneNumber(options);
                    Toast.makeText(getApplicationContext(), "Verification code "+ verificationId,
                            Toast.LENGTH_SHORT).show();
                    navigateToOtpActivity(view, verificationId);
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
