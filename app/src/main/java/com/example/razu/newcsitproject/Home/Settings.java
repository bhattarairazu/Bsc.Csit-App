package com.example.razu.newcsitproject.Home;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.razu.newcsitproject.Adapter.Recycleview_favouritenes;
import com.example.razu.newcsitproject.Database;
import com.example.razu.newcsitproject.Model.Model_favouriteslis;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Sessionmanagement;

import java.util.ArrayList;
import java.util.List;


public class Settings extends AppCompatActivity {
    Toolbar toolbars;
    TextView text_toolbars;
    ImageView btn_back;
    Recycleview_favouritenes madapter;
    Database mbase;
    RecyclerView mviews;
    RecyclerView.LayoutManager manager;
    List<Model_favouriteslis> mfavouritesarraylist = new ArrayList<>();
    Sessionmanagement  managemet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbars =(Toolbar)findViewById(R.id.toolbar);
        //text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
       // btn_back = (ImageView)findViewById(R.id.back_btn);
        //btn_back.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View view) {
             //   finish();

            //}
        //});
        //String names = getIntent().getExtras().getString("name");
        //Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
        //text_toolbars.setText(names);
        //managemet = new Sessionmanagement(this);
        mbase = new Database(this);
        mviews =(RecyclerView)findViewById(R.id.favrecyclerview);
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mviews.setLayoutManager(manager);
        getdatds_favouritelist();
    }
    public void getdatds_favouritelist(){
        Cursor mdatas = mbase.getdata_favourites();
        Log.d("h", "getdatds_favouritelist: kkkkk"+mdatas);
        while(mdatas.moveToNext()){
            Model_favouriteslis mfav = new Model_favouriteslis();
            mfav.setName(mdatas.getString(2));
            mfav.setDescription(mdatas.getString(3));
            mfavouritesarraylist.add(mfav);
            Log.d("h", "getdatds_favouritelist: kkkkk"+mdatas.getString(2)+"dddd"+mdatas.getString(3));
        }
        madapter = new Recycleview_favouritenes(mfavouritesarraylist,this);
        mviews.setAdapter(madapter);
        mviews.invalidate();

       // Log.d("Session", "getda///tds_favouritelist: nnnnnnnnnnnnn"+fav.get(Sessionmanagement.KEY_ID_news)+"jj"+fav.get(Sessionmanagement.KEY_NAME_news));
    }
}
