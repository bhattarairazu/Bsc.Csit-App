package com.example.razu.newcsitproject.Home;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.razu.newcsitproject.R;


/**
 * Created by Razu on 11/29/2017.
 */

public class Eventadds extends AppCompatActivity {
    Toolbar toolbars;
    TextView text_toolbars;
    ImageView btn_back;
    Button add,cancels;
    EditText eventdes,Locatons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);
toolbars = (Toolbar)findViewById(R.id.toolbar);
text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
text_toolbars.setText("Add Event");
btn_back = (ImageView)findViewById(R.id.back_btn);
btn_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
add = (Button)findViewById(R.id.btn_adds);
eventdes = (EditText)findViewById(R.id.eventdescritpion);
final String events = eventdes.getText().toString();
Locatons = (EditText)findViewById(R.id.locations);
final String title = Locatons.getText().toString();
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final int no =1;
        Notification.Builder nt = new Notification.Builder(Eventadds.this)
                .setSmallIcon(R.drawable.header)
                .setContentTitle(title)
                .setContentText(events)
                .addAction(R.drawable.ic_back,"DISMISS",null)
                .addAction(R.drawable.ic_forum,"REPLY",null)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[] { 100, 200, 300 })
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager mmanager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nt.setAutoCancel(true);
        mmanager.notify(no,nt.build());
    }
});

    }


}
