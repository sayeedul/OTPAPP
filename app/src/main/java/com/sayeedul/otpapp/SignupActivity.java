package com.sayeedul.otpapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {


    Button signup;
    EditText userEdt,num,paas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        signup = (Button)findViewById(R.id.signUP);
        userEdt = (EditText)findViewById(R.id.usernameTV);
        num=(EditText)findViewById(R.id.phoneET);
        paas = (EditText)findViewById(R.id.passET);

        signup.setOnClickListener(this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, " PERMISSION NOT GRANTED FOR TEXT MESSAGE ", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String []{Manifest.permission.SEND_SMS},123);
            signup.setEnabled(false);
        }
        else
        {
            Toast.makeText(this, " PERMISSION GRANTED ", Toast.LENGTH_SHORT).show();
            signup.setEnabled(true);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==123)
        {

            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, " PERMISSION GRANTED FOR TEXT ", Toast.LENGTH_SHORT).show();
                signup.setEnabled(true);
            }
            else
            {
                Toast.makeText(this, " PERMISSION NOT GRANTED FOR TEXT ", Toast.LENGTH_SHORT).show();
                signup.setEnabled(false);
            }
        }
    }

    @Override
    public  void onClick(View v) {
        openAlert();
    }

    @Override
    public void onBackPressed() {
        openBackAlert();
    }

    private void openAlert()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignupActivity.this);

        alertDialogBuilder.setTitle("CONFIRM SignUP ");
        alertDialogBuilder.setMessage("Are You Sure To sign up ? ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        alertDialogBuilder.setPositiveButton("YES,Proceed.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(SignupActivity.this, "PLease Wait..Sending OTP... ", Toast.LENGTH_SHORT).show();

                Random rand = new Random();

                int  n = rand.nextInt(9999) + 1000;

                String msg = Integer.toString(n);

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num.getText().toString(),null,msg,null,null);
                Toast.makeText(SignupActivity.this, "OTP SENT SUCCESSFULLY... ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SignupActivity.this,OtpActivity.class);

                String user = userEdt.getText().toString();
                String pass = paas.getText().toString();
                i.putExtra("SentOTP",msg);
                i.putExtra("USERNAME",user);
                i.putExtra("PASSWORD",pass);

                startActivity(i);
            }
        });

        alertDialogBuilder.setNegativeButton("NO,Stay On This Page.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(SignupActivity.this, "Staying On this Page.... ", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void openBackAlert()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignupActivity.this);

        alertDialogBuilder.setTitle("Go BACK ");
        alertDialogBuilder.setMessage("Are You Sure To Cancel ? ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        alertDialogBuilder.setPositiveButton("YES,Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(SignupActivity.this, "PLease Wait..... ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        alertDialogBuilder.setNegativeButton("NO,Stay On This Page.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(SignupActivity.this, "Staying On this Page.... ", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}


