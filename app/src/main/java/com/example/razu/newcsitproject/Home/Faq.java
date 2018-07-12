package com.example.razu.newcsitproject.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.razu.newcsitproject.R;


public class Faq extends AppCompatActivity {
    Toolbar toolbars;
    TextView text_toolbars;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        toolbars =(Toolbar)findViewById(R.id.toolbar);
        text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
        btn_back = (ImageView)findViewById(R.id.back_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        String names = getIntent().getExtras().getString("name");
        Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
        text_toolbars.setText(names);
    }
}
