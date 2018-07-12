package com.example.razu.newcsitproject.Home;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.razu.newcsitproject.Adapter.Recyclerview_forumsview;
import com.example.razu.newcsitproject.Model.Forums_addpost;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Csitforums extends AppCompatActivity {
    Toolbar toolbars;
    ImageView btn_back;
    TextView text_toolbars;
    ProgressBar mprogressbar;
    LinearLayout mlayout;
    FloatingActionButton btns;
    String namess,genders,userids,pictureurl;
    RecyclerView mrecyclervies;
    RecyclerView.LayoutManager mlayoutmanager;
    Recyclerview_forumsview madapters;
    Interface_quotes mapi;
    List<Forums_addpost> mlistdata = new ArrayList<>();
    SwipeRefreshLayout mswipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csitforums);
        toolbars =(Toolbar)findViewById(R.id.ToolBars);
      mprogressbar = (ProgressBar)findViewById(R.id.progressbarsforum) ;
      mlayout=(LinearLayout)findViewById(R.id.errorMessageforum);
        mrecyclervies =(RecyclerView)findViewById(R.id.recyclerview_forums);
        mswipes =(SwipeRefreshLayout)findViewById(R.id.swipe_forusm);
        mprogressbar.setVisibility(View.VISIBLE);
        mprogressbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isconnectd()){
                mprogressbar.setVisibility(View.GONE);
                mswipes.setVisibility(View.VISIBLE);
                getdata_forums();
                }else {
                    mprogressbar.setVisibility(View.GONE);
                    mswipes.setVisibility(View.GONE);
                    mlayout.setVisibility(View.VISIBLE);

                }
            }
        },3000);
        mswipes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mswipes.setRefreshing(true);
                getdata_forums();
                mswipes.setRefreshing(false);
            }
        });

      //  //btn_back.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View view) {
               // finish();

           // }
       // });
        setSupportActionBar(toolbars);
        setTitle("Csit Forums");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String toolbars_nae = getIntent().getExtras().getString("name_toolbar");
         namess = getIntent().getExtras().getString("names");
         genders = getIntent().getExtras().getString("gender");
         userids = getIntent().getExtras().getString("ids");
         pictureurl = getIntent().getExtras().getString("picurl");
        //Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
        //text_toolbars.setText(toolbars_nae);

        btns = (FloatingActionButton)findViewById(R.id.floatingbtn);
        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(Csitforums.this,Add_forums.class);
                intents.putExtra("namess",namess);
                intents.putExtra("userid",userids);
                intents.putExtra("genders",genders);
                intents.putExtra("picurls",pictureurl);
                startActivity(intents);
            }
        });
        mlayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mrecyclervies.setLayoutManager(mlayoutmanager);
        getdata_forums();

    }
    public boolean isconnectd(){
        ConnectivityManager mconn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo minfo =mconn.getActiveNetworkInfo();
        if(minfo != null && minfo.isConnected()){
            return true;
        }
        return false;
    }
    public void getdata_forums(){
        mapi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        final Call<List<Forums_addpost>> mlistforums = mapi.mgetdata();
        mlistforums.enqueue(new Callback<List<Forums_addpost>>() {
            @Override
            public void onResponse(Call<List<Forums_addpost>> call, Response<List<Forums_addpost>> response) {
              mlistdata = response.body();
                Log.d("lats", "onResponse: nnnnnnnnn"+mlistdata);
              mrecyclervies.setAdapter(new Recyclerview_forumsview(mlistdata,getApplicationContext(),namess,pictureurl));
             // madapters.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Forums_addpost>> call, Throwable t) {
             mlayout.setVisibility(View.VISIBLE);
            }
        });
    }

}
