package com.example.telecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button signIn;
    EditText uName, uPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signIn = (Button) findViewById(R.id.btn_signIn);
        uName = (EditText) findViewById(R.id.edit_text_phone_no);
        uPwd = (EditText) findViewById(R.id.edit_text_pwd);

        String name = uName.getText().toString();
        String pwd = uName.getText().toString();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                 Toast t = Toast.makeText(getApplicationContext(), "All Fields Required",
                         Toast.LENGTH_SHORT);
                 t.show();
                }else {
                    Toast.makeText(getApplicationContext(), "Button click",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}