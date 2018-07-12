package com.example.razu.newcsitproject.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.razu.newcsitproject.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;


public class Profile extends AppCompatActivity {
    Toolbar toolbars;
    ImageView btn_back;
    TextView text_toolbars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //toolbars =(Toolbar)findViewById(R.id.toolbar);
       // text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
        //btn_back = (ImageView)findViewById(R.id.back_btn);
        //btn_back.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
               //finish();

           // }
     //   });
       // String names = getIntent().getExtras().getString("name");
        //Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
       // text_toolbars.setText(names);
        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.green)
                .setCover(R.drawable.header)
                .setName("Acepirit Pvt.Ltd")
                .setSubTitle("Mobile Development/Web-Designing")
                .setBrief("Acepirit is one of the best IT company situated in K-town.It provides the quality services to the client.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("user")
                .addFacebookLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        addContentView(view,lp);
    }

}
