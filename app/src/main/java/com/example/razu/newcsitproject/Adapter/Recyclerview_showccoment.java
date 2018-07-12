package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.razu.newcsitproject.Model.Comment_add;
import com.example.razu.newcsitproject.Model.Comment_getda;
import com.example.razu.newcsitproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Razu on 12/1/2017.
 */

public class Recyclerview_showccoment extends RecyclerView.Adapter<Recyclerview_showccoment.MyViewHolder> {
    List<Comment_getda> mcommentlist;
    private Context mcontext;
  //String pucure;


    public Recyclerview_showccoment(List<Comment_getda> mcommentlist, Context mcontext) {
        this.mcommentlist = mcommentlist;
        this.mcontext = mcontext;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vies = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comments,parent,false);

        return new MyViewHolder(vies);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comment_getda addsss = mcommentlist.get(position);
        holder.comments.setText(addsss.getComment());
        holder.names.setText(addsss.getNames());
        Picasso.with(mcontext).load(addsss.getPic()).into(holder.afnoimage);


    }

    @Override
    public int getItemCount() {
        return mcommentlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView names,comments;
        ImageView afnoimage;
        public MyViewHolder(View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.Textview_name);
            comments = (TextView)itemView.findViewById(R.id.commets);
            afnoimage =(ImageView) itemView.findViewById(R.id.profile_image);
        }
    }
}
