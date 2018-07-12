package com.example.razu.newcsitproject.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.razu.newcsitproject.Adapter.Recyclerview_news;
import com.example.razu.newcsitproject.Adapter.Recyclerview_newsdetails;
import com.example.razu.newcsitproject.Adapter.Recyclerview_timelineview;
import com.example.razu.newcsitproject.Database;
import com.example.razu.newcsitproject.Model.Cardview_newsource;
import com.example.razu.newcsitproject.Model.Innersource;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_source;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Razu on 12/26/2017.
 */

public class Fragment_news extends Fragment {
    RecyclerView mrecyclerview;
    RecyclerView.LayoutManager mlayoutmanager;
    LinearLayout mlayout;
    ProgressBar mprogressbar;
    Database mdatabase ;
    Interface_quotes mapiinterface;
    Recyclerview_news mrecycleradater;
    List<Innersource> mlist = new ArrayList<>();

    public Fragment_news() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }
   // View views = inflater.inflate(R.layout.fragment_sun, container, false);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_news, container, false);
        mdatabase = new Database(views.getContext());
        mprogressbar = (ProgressBar)views.findViewById(R.id.progressbarnewse);
        mlayout =(LinearLayout)views.findViewById(R.id.errorMessageNews);
          mrecyclerview =(RecyclerView)views.findViewById(R.id.recyclers_fragmentnews);
        mprogressbar.setVisibility(View.VISIBLE);

        getnewspai();
        mprogressbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isconnectd()) {
                    mprogressbar.setVisibility(View.GONE);
                    //mswiperefresh.setVisibility(View.VISIBLE);
                    mrecyclerview.setVisibility(View.VISIBLE);

                }else {
                    mprogressbar.setVisibility(View.GONE);
                    mrecyclerview.setVisibility(View.GONE);
                    mlayout.setVisibility(View.VISIBLE);
                }
            }
        }, 5000);

        mlayoutmanager =new LinearLayoutManager(views.getContext(),LinearLayoutManager.VERTICAL,false);
          mrecyclerview.setLayoutManager(mlayoutmanager);

        return views;
    }
    public void getnewspai(){
        mapiinterface = Apiclient_source.getApiclient().create(Interface_quotes.class);
        Call<Cardview_newsource> mnews = mapiinterface.mnewssources();
        mnews.enqueue(new Callback<Cardview_newsource>() {
            @Override
            public void onResponse(Call<Cardview_newsource> call, Response<Cardview_newsource> response) {
                List<Innersource> minner = response.body().getSources();
                mrecycleradater = new Recyclerview_news(minner,getApplicationContext());
                mrecyclerview.setAdapter(mrecycleradater);
            }

            @Override
            public void onFailure(Call<Cardview_newsource> call, Throwable t) {

            }
        });


    }
    public boolean isconnectd(){
        ConnectivityManager mconn = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo minfo =mconn.getActiveNetworkInfo();
        if(minfo != null && minfo.isConnected()){
            return true;
        }
        return false;
    }
}
