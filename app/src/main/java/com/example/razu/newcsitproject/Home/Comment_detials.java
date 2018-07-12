package com.example.razu.newcsitproject.Home;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.razu.newcsitproject.Adapter.Recyclerview_showccoment;
import com.example.razu.newcsitproject.Model.Comment_add;
import com.example.razu.newcsitproject.Model.Comment_getda;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_detials extends AppCompatActivity {
String post,names,pictures,defaultname,defaultpicture;
int postid;
ImageView images;
TextView namess_text,detials_text;
EditText textcomment;
Button btndaddl;
    Interface_quotes mqpi;
    List<Comment_getda> mcomslist =  new ArrayList<>();
    RecyclerView mrecycler;
    RecyclerView.LayoutManager mlayounamater;
    Recyclerview_showccoment madapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detials);
        postid = getIntent().getExtras().getInt("idn");
        Log.d("Comment", "onCreate: kkkkkkkkkkkkkkkkk"+postid);
        names = getIntent().getExtras().getString("names");
        post = getIntent().getExtras().getString("post");
        pictures = getIntent().getExtras().getString("picurl");
        defaultname = getIntent().getExtras().getString("names_id");
        defaultpicture=getIntent().getExtras().getString("ppic");
        images = (ImageView)findViewById(R.id.profile_image);
        namess_text = (TextView)findViewById(R.id.text_profilename);
        detials_text = (TextView)findViewById(R.id.postdescription);
        textcomment = (EditText)findViewById(R.id.text_comment);
        btndaddl =(Button)findViewById(R.id.buttonsubmit);
        final String comment = textcomment.getText().toString();
        namess_text.setText(names);
        detials_text.setText(post);
        Picasso.with(this).load(pictures).into(images);
        btndaddl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_comment(postid,defaultname,defaultpicture);
               // madapters.notifyDataSetChanged();
            }
        });
        mrecycler = (RecyclerView)findViewById(R.id.recyclerview_commentdetails);
        mlayounamater = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mrecycler.setLayoutManager(mlayounamater);
       get_comments(postid);



    }
    public void add_comment(int postid,String names,String picurl){
        String comment = textcomment.getText().toString();

        mqpi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        Call<Comment_add> mcommentadd = mqpi.postcomment(postid,names,picurl,comment);
        mcommentadd.enqueue(new Callback<Comment_add>() {
            @Override
            public void onResponse(Call<Comment_add> call, Response<Comment_add> response) {
                Comment_add mcom = response.body();
                Toast.makeText(getApplicationContext(),"ldldld"+mcom.getResponse(),Toast.LENGTH_SHORT).show();
                textcomment.setText("");
            }

            @Override
            public void onFailure(Call<Comment_add> call, Throwable t) {

            }
        });
    }
    public void get_comments(int postid){
        mqpi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
        Call<List<Comment_getda>> da = mqpi.mccc(postid);
        da.enqueue(new Callback<List<Comment_getda>>() {
            @Override
            public void onResponse(Call<List<Comment_getda>> call, Response<List<Comment_getda>> response) {
                mcomslist = response.body();
                Log.d("lat", "onResponse: kkkkkkkkkkkkkkkkkkk"+mcomslist);
                mrecycler.setAdapter(new Recyclerview_showccoment(mcomslist,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Comment_getda>> call, Throwable t) {

            }
        });
    }

}
