package com.sayeedul.otpapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginBtn,resetBtn;
    EditText userEdt,pwdEdt;
    TextView registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button)findViewById(R.id.loginbutton);
        resetBtn = (Button)findViewById(R.id.resetbutton);

        userEdt = (EditText)findViewById(R.id.userNameEditText);
        pwdEdt = (EditText)findViewById(R.id.pwdEditText);

        registerTV = (TextView)findViewById(R.id.registerLink);

        loginBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        registerTV.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.loginbutton :
                String userName = userEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();

               // Intent intent = getIntent();
              //  String x = intent.getStringExtra("USERNAME");
                //String y = intent.getStringExtra("PASSWORD");

                if(userName.equalsIgnoreCase("x") && pwd.equals("y"))
                {
                    openLoginAlert();
                }
                else
                {
                    Toast.makeText(this,"LOGIN DETAILS ARE INCORRECT...",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.resetbutton :
                openResetAlert();
                break;

            case R.id.registerLink :
                Toast.makeText(this,"You Are Redirected to SIGN_UP page.....",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);

                break;
        }
    }


    private void openLoginAlert()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("CONFIRM LOGIN ");
        alertDialogBuilder.setMessage("Are You Sure To LOGIN ? ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        alertDialogBuilder.setPositiveButton("YES,Proceed to Login.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "PLease Wait.... ", Toast.LENGTH_SHORT).show();

                String userName = userEdt.getText().toString();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                i.putExtra("username",userName);
                startActivity(i);
            }
        });

        alertDialogBuilder.setNegativeButton("NO,Stay On This Page.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Staying On this Page.... ", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void openResetAlert()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("CONFIRM RESET ");
        alertDialogBuilder.setMessage("Are You Sure To Reset? ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        alertDialogBuilder.setPositiveButton("YES,Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "PLease Wait.... ", Toast.LENGTH_SHORT).show();
                userEdt.setText("");
                pwdEdt.setText("");

            }
        });

        alertDialogBuilder.setNegativeButton("NO,Don't Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "NOT RESETTING.... ", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}

