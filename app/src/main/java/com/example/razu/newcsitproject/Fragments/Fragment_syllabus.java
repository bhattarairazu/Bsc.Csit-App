package com.example.razu.newcsitproject.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

//import com.example.razu.newcsitproject.Adapter.Recyclerview_download_syllabus_firstsem;
import com.example.razu.newcsitproject.Adapter.Recyclerview_downloadfiles;
import com.example.razu.newcsitproject.Model.Model_syllabus_array;
import com.example.razu.newcsitproject.Model.Model_syllabus_main;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;
import com.example.razu.newcsitproject.Semester.Firstsem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Razu on 12/1/2017.
 */

public class Fragment_syllabus extends Fragment {
    Interface_quotes mapi;
    RecyclerView mrecycler;
    LinearLayout mlayout;
    ProgressBar mprogressbar;
    RecyclerView.LayoutManager mlayoutmanager;
    List<Model_syllabus_array> mlistview = new ArrayList<>();
    SwipeRefreshLayout mrefresh;
    int semester;
    public Fragment_syllabus() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);
        mlayout = (LinearLayout)view.findViewById(R.id.errorMessageold);
        mprogressbar =(ProgressBar)view.findViewById(R.id.progressbarold);

        mrecycler = (RecyclerView)view.findViewById(R.id.recyclerview_fieldownload);
        mrefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_oldquestions);
      mprogressbar.setVisibility(View.VISIBLE);
        Firstsem mfirstsem = (Firstsem)getActivity();
        semester=mfirstsem.getdata();
        Log.d("first", "onCreateView: bbbbbbbbbbbb"+semester);
        if(isconnectd()) {
            mprogressbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mprogressbar.setVisibility(View.GONE);
                    mrefresh.setVisibility(View.VISIBLE);
                  getdata();
                }
            }, 3000);
        }else {
            mprogressbar.setVisibility(View.GONE);
            mlayout.setVisibility(View.VISIBLE);
        }
        mrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mrefresh.setRefreshing(true);
               getdata();
                mrefresh.setRefreshing(false);
            }
        });
        mlayoutmanager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        mrecycler.setLayoutManager(mlayoutmanager);
        //getdata_forums();
       getdata();

        return view;
    }
    //checking internet connection
    public boolean isconnectd(){
        ConnectivityManager mconn = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo minfo =mconn.getActiveNetworkInfo();
        if(minfo != null && minfo.isConnected()){
            return true;
        }
        return false;
    }
    /*public void getdata(){
        mapi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        Call<List<Model_syllabus_firstsemester>> msyllabusfirstsme = mapi.mdownloadsylabusfirstsem();
        msyllabusfirstsme.enqueue(new Callback<List<Model_syllabus_firstsemester>>() {
            @Override
            public void onResponse(Call<List<Model_syllabus_firstsemester>> call, Response<List<Model_syllabus_firstsemester>> response) {
                mlistview = response.body();
                Log.d("h", "onResponse: ..............."+mlistview.toString());
                mrecycler.setAdapter(new Recyclerview_download_syllabus_firstsem(mlistview,getContext()));
            }

            @Override
            public void onFailure(Call<List<Model_syllabus_firstsemester>> call, Throwable t) {

            }
        });
    }*/
    public void getdata(){
        mapi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        Call<Model_syllabus_main> msyllabus = mapi.mgetsyllabus(semester);
        msyllabus.enqueue(new Callback<Model_syllabus_main>() {
            @Override
            public void onResponse(Call<Model_syllabus_main> call, Response<Model_syllabus_main> response) {
                mlistview = response.body().getResult();
                mrecycler.setAdapter(new Recyclerview_downloadfiles(mlistview,getContext(),"syllabus"));
            }

            @Override
            public void onFailure(Call<Model_syllabus_main> call, Throwable t) {
                Log.d("failed", "onFailure: failes"+t.toString());
            }
        });
    }
}

