package com.example.razu.newcsitproject.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.razu.newcsitproject.Adapter.Recyclerview_semester;
import com.example.razu.newcsitproject.Model.Cardview_itemsimage;
import com.example.razu.newcsitproject.R;

import java.util.ArrayList;
import java.util.List;


public class Semester extends AppCompatActivity {
    Toolbar toolbars;
    TextView text_toolbars;
    ImageView btn_back;
    RecyclerView views_recycler;
    List<Cardview_itemsimage> mlist;
    RecyclerView.LayoutManager mlayoutmanager;
    Recyclerview_semester msemesteradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);
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
        mlist = new ArrayList<>();
        msemesteradapter = new Recyclerview_semester(mlist,this);
        text_toolbars.setText(names);
        views_recycler =(RecyclerView)findViewById(R.id.recycerlview_semseter);
        mlayoutmanager = new GridLayoutManager(this,2);
        views_recycler.setLayoutManager(mlayoutmanager);
        views_recycler.setAdapter(msemesteradapter);
        //mlist = new ArrayList<>();
        preparealbmuns();
        //views_recycler.setAdapter(msemesteradapter);

    }
    public void preparealbmuns(){
        int[] covers = new int[]{
                R.drawable.bg,
                R.drawable.bg,
                R.drawable.bg,
                R.drawable.bg,R.drawable.bg,
                R.drawable.bg,R.drawable.bg,
                R.drawable.bg,
        };
        Cardview_itemsimage cimages = new Cardview_itemsimage(covers[0]);

        mlist.add(cimages);
        Cardview_itemsimage cimages1 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages1);
        Cardview_itemsimage cimages2 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages2);
        Cardview_itemsimage cimages3 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages3);
        Cardview_itemsimage cimages4 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages4);
        Cardview_itemsimage cimages5 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages5);
        Cardview_itemsimage cimages6 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages6);
        Cardview_itemsimage cimages7 = new Cardview_itemsimage(covers[0]);
        mlist.add(cimages7);
msemesteradapter.notifyDataSetChanged();
    }
}
