package com.example.razu.newcsitproject.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.razu.newcsitproject.Adapter.Recyclerview_newsdetails;
import com.example.razu.newcsitproject.Model.Data;
import com.example.razu.newcsitproject.Model.Datamodel;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_newsdetails;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailsnewssoource extends AppCompatActivity {
    RecyclerView recyclerivies;
    RecyclerView.LayoutManager mlayoutmanager;
    Interface_quotes mapiinterface;
    Recyclerview_newsdetails mnewsdetaisla;
    public String apiKey = "27aff5efe50b433c8926e46700ab9901";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsnewssoource);
         String soucres = getIntent().getExtras().getString("newsname");
        Log.d("Details", "onCreate: dddddddddddd"+soucres);
        getrestpai(soucres);
        recyclerivies =(RecyclerView)findViewById(R.id.recyclers_detailsnews);
        mlayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerivies.setLayoutManager(mlayoutmanager);


    }
   public void getrestpai(String sourc){
        final String sources = sourc;
    mapiinterface = Apiclient_newsdetails.getApiclient().create(Interface_quotes.class);
    Call<Datamodel> mdatamodel = mapiinterface.getTopheadlinesnews(sources,apiKey);
    mdatamodel.enqueue(new Callback<Datamodel>() {
        @Override
        public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {
            List<Data> das = response.body().getArticles();
            recyclerivies.setAdapter(new Recyclerview_newsdetails(getApplication(),das,sources));
        }

        @Override
        public void onFailure(Call<Datamodel> call, Throwable t) {

        }
    });

    }
}