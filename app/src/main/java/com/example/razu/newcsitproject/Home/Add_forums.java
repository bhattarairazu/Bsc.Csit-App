package com.example.razu.newcsitproject.Home;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.razu.newcsitproject.Model.Forums_addpost;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add_forums extends AppCompatActivity {
  Interface_quotes mqpi;
    EditText postdess;
    Button cancel,adds;
    String name,gender,fbid,picurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forums);
        postdess = (EditText)findViewById(R.id.editText_addpost);
        adds =(Button)findViewById(R.id.btn_adds);
        cancel = (Button)findViewById(R.id.cancel_btns);
        name = getIntent().getExtras().getString("namess");
        gender = getIntent().getExtras().getString("genders");
     fbid = getIntent().getExtras().getString("userid");
        picurl = getIntent().getExtras().getString("picurls");
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restpai_postforumsdata();
                sendnotifications();
            }
        });

    }
    public void sendnotifications(){
        final int no =1;
        Notification.Builder nt = new Notification.Builder(Add_forums.this)
                .setSmallIcon(R.drawable.profile)
                .setContentTitle(name+" Posted on a Forum.")
               //.setContentText(events)
                .addAction(R.drawable.ic_back,"DISMISS",null)
                .addAction(R.drawable.ic_forum,"REPLY",null)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[] { 100, 200, 300 })
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager mmanager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nt.setAutoCancel(true);
        mmanager.notify(no,nt.build());
    }
    public void restpai_postforumsdata(){
         String commentdes = postdess.getText().toString();
        final long timestamps = getcurrentimemilli();
        mqpi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        Call<Forums_addpost> maddpost = mqpi.postdata(name,fbid,gender,picurl,commentdes,timestamps);
        maddpost.enqueue(new Callback<Forums_addpost>() {
            @Override
            public void onResponse(Call<Forums_addpost> call, Response<Forums_addpost> response) {
           Forums_addpost mresult = response.body();
                Toast.makeText(getApplicationContext(),mresult.getResponse(),Toast.LENGTH_SHORT).show();
                postdess.setText("");
            }

            @Override
            public void onFailure(Call<Forums_addpost> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });



    }
    public long dates(String datess){
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        try {

            //String date = df.format(Calendar.getInstance().getTime());
            Date mDate = df.parse(datess);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);

            //long seconds=(timeInMilliseconds/1000)%60;

            //long minutes=((timeInMilliseconds-seconds)/1000)/60;
            //long hour = minutes/60;
            //Log.d("date", "onCreate: tiiiiiiiiiiiiiiiiiiiiii"+minutes+"/"+seconds+"/"+hour);
            return timeInMilliseconds;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public long getcurrentimemilli(){
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        long currenttime = dates(date);
        //String curtime =currenttime
        return currenttime;
    }
}
