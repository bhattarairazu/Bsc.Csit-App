package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.razu.newcsitproject.Checkforsdcard;
import com.example.razu.newcsitproject.Downloadclass;
import com.example.razu.newcsitproject.Model.Forums_addpost;
import com.example.razu.newcsitproject.Model.Model_downloadfiles;
import com.example.razu.newcsitproject.Model.Model_syllabus_array;
import com.example.razu.newcsitproject.Model.Model_syllabus_main;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Semester.Utils;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Razu on 12/5/2017.
 */

public class Recyclerview_downloadfiles extends RecyclerView.Adapter<Recyclerview_downloadfiles.MyViewHolder> {

   private List<Model_syllabus_array> mlistviwe;
   private Downloadclass mdownlodaclass;
    private Context mcontxt;
    private String data;

    public Recyclerview_downloadfiles(List<Model_syllabus_array> mlistviwe, Context mcontxt,String data) {
        this.mlistviwe = mlistviwe;
        this.mcontxt = mcontxt;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dwonloadfiels,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
   Model_syllabus_array mdatamodel = mlistviwe.get(position);
   holder.nmaes.setText(mdatamodel.getTitle());
    }


    @Override
    public int getItemCount() {
        return mlistviwe.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nmaes;
        ImageView mimage;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mimage =(ImageView)itemView.findViewById(R.id.mimage_download);
            nmaes = (TextView)itemView.findViewById(R.id.Textview_name);
            mimage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(internetconntection()) {
                mdownlodaclass = new Downloadclass(mcontxt, mlistviwe.get(getAdapterPosition()).getFile(),data);
                Log.d("guu", "onClick: bbbbbbbbbbbbbbbbbbbbbbbbbbb+" + mlistviwe.get(getAdapterPosition()).getFile());
            }else {
                Toast.makeText(mcontxt, "Please connect to the internet...", Toast.LENGTH_SHORT).show();
            }
        }}
        public boolean internetconntection(){
            ConnectivityManager connectivityManager = (ConnectivityManager) mcontxt.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo minfo = connectivityManager.getActiveNetworkInfo();
            if(minfo != null && minfo.isConnected() ){
                return true;
            }
            return false;

        }
}
