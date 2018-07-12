package com.example.razu.newcsitproject;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.razu.newcsitproject.Home.Dashboard;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView btn_createaccount,btn_signin;
    LoginButton btn;
    CallbackManager mcallback;
    Sessionmanagement msession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        msession = new Sessionmanagement(this);
        btn_createaccount = (TextView)findViewById(R.id.createaccount);
        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity_Signup.class);
                startActivity(intent);
            }
        });
        btn_signin=(TextView)findViewById(R.id.signin_btn);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        btn = (LoginButton)findViewById(R.id.fblogin);
        mcallback = CallbackManager.Factory.create();
        btn.registerCallback(mcallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = new GraphRequest().newMeRequest(loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                response.getError();
                                //if(){}
                                try {
                                    if(Build.VERSION.SDK_INT > 9){
                                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                        StrictMode.setThreadPolicy(policy);
                                        String profilepic = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                        //String coverpic = object.getJSONObject("cover").getString("source");
                                        //String email = object.getString("email");
                                        Log.d("kkkkk", "onCompleted: uuuuuuuuuuuuu"+profilepic);
                                        String name = object.getString("name");
                                        String gender = object.getString("gender");
                                        Log.d("kkkkk", "onCompleted: uuuuuuuuuuuuu"+name+"ge"+gender);
                                        URL fburl = new URL(profilepic);
                                        HttpURLConnection connections = (HttpURLConnection)fburl.openConnection();
                                        HttpURLConnection.setFollowRedirects(true);
                                        connections.setInstanceFollowRedirects(true);
                                        //Bitmap fb_img = BitmapFactory.decodeStream(connections.getInputStream());
                                        //fbimagessss.setImageBitmap(fb_img);
                                        msession.createloginsession(loginResult.getAccessToken().getUserId(),name,gender,profilepic);
                                        Intent intent = new Intent(MainActivity.this,Dashboard.class);
                                        intent.putExtra("appid",loginResult.getAccessToken().getUserId());
                                        intent.putExtra("name",name);
                                        //intent.putExtra("userid",l)
                                        intent.putExtra("gender",gender);
                                        intent.putExtra("image",profilepic);
                                        startActivity(intent);


                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,gender,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();











            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("p", "onError: kkkkkkkkkkkkkkk"+error.toString());
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mcallback.onActivityResult(requestCode,resultCode,data);
    }
}