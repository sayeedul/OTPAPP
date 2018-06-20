package com.sayeedul.otpapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {

    EditText otp;
    Button verify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_main);

        Intent i = getIntent();
        final String user = i.getStringExtra("USERNAME");
        final String pass = i.getStringExtra("PASSWORD");
        final String getOTP = i.getStringExtra("SentOTP");


        otp = (EditText)findViewById(R.id.otpET);
        verify = (Button)findViewById(R.id.verifyBTN);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String OTP = otp.getText().toString();

                if(OTP.equals(getOTP))
                {
                    Toast.makeText(OtpActivity.this, "OTP VERIFIED SUCCESSFULLY...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(OtpActivity.this,MainActivity.class);
                    intent.putExtra("USERNAME",user);
                    intent.putExtra("PASSWORD",pass);
                    startActivity(intent);
                }

            }
        });

    }
}
