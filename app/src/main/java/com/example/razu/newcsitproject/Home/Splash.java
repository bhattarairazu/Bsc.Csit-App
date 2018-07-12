package com.example.razu.newcsitproject.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.razu.newcsitproject.MainActivity;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Sessionmanagement;

import java.util.HashMap;


public class Splash extends AppCompatActivity {
private static final int SPLASH_TIMEOUT=3000;
Sessionmanagement msession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        msession = new Sessionmanagement(this);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 //Intent intents = new Intent(Splash.this, MainActivity.class);
                 //startActivity(intents);
                 //finish();
                 if(msession.isLoggedIn()){
                     HashMap<String,String> user = msession.getuserdetails();
                     String name = user.get(Sessionmanagement.KEY_NAME);
                     String fbid = user.get(Sessionmanagement.KEY_FBID);
                     String gender = user.get(Sessionmanagement.KEY_GENDER);
                     String image = user.get(Sessionmanagement.KEY_IMAGE);
                     Intent i = new Intent(getApplicationContext(),Dashboard.class);
                     i.putExtra("name",name);
                     i.putExtra("appid",fbid);
                     i.putExtra("gender",gender);
                     i.putExtra("image",image);
                     startActivity(i);
                     finish();
                 }
                 msession.checkloginstatus();
                 finish();
             }
         },SPLASH_TIMEOUT);

    }
}
