package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.razu.newcsitproject.Home.Webviews;
import com.example.razu.newcsitproject.Model.Data;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Semester.Utils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Razu on 12/1/2017.
 */

public class Recyclerview_newsdetails extends RecyclerView.Adapter<Recyclerview_newsdetails.MyViewHolder> {
    private Context mcontext;
    List<Data> mdatamodel;
    private String SOURCE;


   // public Recyclerview_newsdetails(Context mcontext, List<Data> mdatamodel,String Ssources) {
       // this.mcontext = mcontext;
       // this.mdatamodel = mdatamodel;
   // }


    public Recyclerview_newsdetails(Context mcontext, List<Data> mdatamodel, String SOURCE) {
        this.mcontext = mcontext;
        this.mdatamodel = mdatamodel;
        this.SOURCE = SOURCE;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_newssoucredetails,parent,false);

        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data model = mdatamodel.get(position);
        //Datamodel pos = models.get(position);
        holder.newstimes.setText(convertDate(model.getPublishedAt()));
        holder.news_detia.setText(model.getTitle());
        holder.news_sub.setText(model.getDescription());
        holder.urls.setText(model.getUrl());
        holder.news_head.setText(SOURCE.toUpperCase());
        Picasso.with(mcontext).load(model.getUrlToImage()).into(holder.imageView_desc);
    }

    @Override
    public int getItemCount() {
        return mdatamodel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView_desc,newsimage2s;
        TextView news_head,news_detia,news_sub,urls;
        TextView newstimes;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //imageView_desc.setOnClickListener(this);
            imageView_desc =(ImageView)itemView.findViewById(R.id.imageview_newsbigimage);
            news_head = (TextView)itemView.findViewById(R.id.textvew_newsheading);
            newstimes = (TextView) itemView.findViewById(R.id.textview_time);
            //newstimes.setReferenceTime(new Date().getTime());
            news_detia = (TextView)itemView.findViewById(R.id.textview_heading);
            news_sub = (TextView)itemView.findViewById(R.id.textview_subheading);
            urls = (TextView)itemView.findViewById(R.id.textview_urls);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Webviews.class);
            intent.putExtra("url",mdatamodel.get(getAdapterPosition()).getUrl());
            view.getContext().startActivity(intent);
        }
    }
    public static CharSequence convertDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        try {
            Date finalDate = simpleDateFormat.parse(date);
            return DateUtils.getRelativeTimeSpanString(finalDate.getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE);
        } catch (Exception e) {
            return "Unknown Time";
        }
    }
}
