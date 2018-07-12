package com.example.razu.newcsitproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Razu on 11/23/2017.
 */

public class Activity_Signup extends AppCompatActivity {
TextView btn_signup,btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        btn_signin = (TextView)findViewById(R.id.signin_btn);
        btn_signup = (TextView)findViewById(R.id.signup_btn);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastmessage("Sucessfully signup");
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void toastmessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
