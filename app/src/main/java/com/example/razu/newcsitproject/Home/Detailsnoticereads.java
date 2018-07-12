package com.example.razu.newcsitproject.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.razu.newcsitproject.R;

public class Detailsnoticereads extends AppCompatActivity {
TextView details,dates,titless;
Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsnoticereads);
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        setTitle("Notice Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    details = (TextView)findViewById(R.id.textview_detaislnoticedetails);
    dates = (TextView)findViewById(R.id.textview_detailsnoticedates);
    titless = (TextView)findViewById(R.id.textview_detailsnotices);

    String detials = getIntent().getExtras().getString("des");
    String datess = getIntent().getExtras().getString("dates");
    String titles = getIntent().getExtras().getString("titles");
    details.setText(detials);
    dates.setText(datess);
    titless.setText(titles);
    }
}
