package com.example.razu.newcsitproject.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.razu.newcsitproject.Adapter.Recycleview_favouritenes;
import com.example.razu.newcsitproject.Database;
import com.example.razu.newcsitproject.Model.Model_favouriteslis;
import com.example.razu.newcsitproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Razu on 12/23/2017.
 */

public class Fragment_favourtenews extends Fragment {
    Recycleview_favouritenes madapter;
    Database mbase;
    RecyclerView mviews;
    RecyclerView.LayoutManager manager;
    List<Model_favouriteslis> mfavouritesarraylist = new ArrayList<>();
    public Fragment_favourtenews() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favouritenews,container,false);
        mbase = new Database(view.getContext());
        mviews =(RecyclerView)view.findViewById(R.id.recyclers_fragmentfavourite);



        manager =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mviews.setLayoutManager(manager);
        getdatds_favouritelist();




        return view;
    }
    public void getdatds_favouritelist(){
        Cursor mdatas = mbase.getdata_favourites();
        Log.d("h", "getdatds_favouritelist: kkkkk"+mdatas);
        while(mdatas.moveToNext()){
            Model_favouriteslis mfav = new Model_favouriteslis();
            mfav.setData_id(mdatas.getInt(0));
            mfav.setName(mdatas.getString(2));
            mfav.setDescription(mdatas.getString(3));
            mfavouritesarraylist.add(mfav);
            Log.d("h", "getdatds_favouritelist: kkkkk"+mdatas.getString(2)+"dddd"+mdatas.getString(3));
        }
        madapter = new Recycleview_favouritenes(mfavouritesarraylist,getContext());
        mviews.setAdapter(madapter);
        mviews.invalidate();

        // Log.d("Session", "getda///tds_favouritelist: nnnnnnnnnnnnn"+fav.get(Sessionmanagement.KEY_ID_news)+"jj"+fav.get(Sessionmanagement.KEY_NAME_news));
    }
}
