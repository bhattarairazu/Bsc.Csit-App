package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.razu.newcsitproject.Database;
import com.example.razu.newcsitproject.Home.Detailsnewssoource;
import com.example.razu.newcsitproject.Model.Innersource;
import com.example.razu.newcsitproject.Model.Model_favouriteslis;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Sessionmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Razu on 12/1/2017.
 */

public class Recyclerview_news extends RecyclerView.Adapter<Recyclerview_news.MyViewHolder> {

List<Innersource> minnersouce;

private Context mcontext;

    public Recyclerview_news(List<Innersource> minnersouce, Context mcontext) {
        this.minnersouce = minnersouce;
        this.mcontext = mcontext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_newssourceitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    Innersource sourcesss = minnersouce.get(position);
    holder.text_des.setText(sourcesss.getDescription());
    holder.texts.setText(sourcesss.getName());
    }

    @Override
    public int getItemCount() {
        return minnersouce.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imq,popupmenu;
        TextView texts,text_des;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imq = (ImageView)itemView.findViewById(R.id.news_image);
            texts = (TextView)itemView.findViewById(R.id.news_textview);
            popupmenu =(ImageView)itemView.findViewById(R.id.popupimage);
            text_des =(TextView)itemView.findViewById(R.id.news_description);
            texts.setOnClickListener(this);
            popupmenu.setOnClickListener(this);
            text_des.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          int id = getAdapterPosition();
            //Log.d("rr", "onClick: vvvvvvvvvvv"+view.getId());
            switch (view.getId()){
                case R.id.news_description:
                     Intent intens = new Intent(view.getContext(), Detailsnewssoource.class);
                     intens.putExtra("newsname",minnersouce.get(id).getId());
                    view.getContext().startActivity(intens);
                    break;
                case R.id.news_image:
                     Intent intenss = new Intent(view.getContext(), Detailsnewssoource.class);
                    intenss.putExtra("newsname",minnersouce.get(id).getId());
                    view.getContext().startActivity(intenss);
                     break;
                case R.id.popupimage:
                    showpopupmenu(view,minnersouce.get(id).getId(),minnersouce.get(id).getName(),minnersouce.get(id).getDescription());
                    break;

            }
        }
    }
    public void showpopupmenu(View view,String id,String namef,String descf){
        PopupMenu pop = new PopupMenu(mcontext,view);
        MenuInflater mmenu = pop.getMenuInflater();
        mmenu.inflate(R.menu.menu,pop.getMenu());
        pop.setOnMenuItemClickListener(new MyMenuitemclicklistener(id,namef,descf));
        pop.show();
    }

    private class MyMenuitemclicklistener implements PopupMenu.OnMenuItemClickListener {
        String itemid;
        String fnmes,fdescription;
        List<Model_favouriteslis> mfavourites = new ArrayList<>();
        public MyMenuitemclicklistener(String  id,String fav_names,String fav_desc) {
            this.itemid = id;
            this.fnmes = fav_names;
            this.fdescription = fav_desc;

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Database mdatabase = new Database(mcontext);
            Toast.makeText(mcontext,"Added to Favourites",Toast.LENGTH_SHORT).show();
            ////Model_favouriteslis lists = new Model_favouriteslis();
            //lists.setId(itemid);
            //mfavourites.add(lists);
            //mmanagement = new Sessionmanagement(mcontext);
            //mmanagement.insertfavourtie_news(itemid,fnmes,fdescription);
            //Model_favouriteslis mfavouriteslist = new Model_favouriteslis(itemid,fnmes,fdescription);
            mdatabase.add_favouritenews(itemid,fnmes,fdescription);
            Log.d("e", "onMenuItemClick: ,,,,,,,"+itemid+"'"+fnmes+"["+fdescription);
            return false;
        }

    }

    public void setfilter(ArrayList<Innersource> mfilterlist){
        minnersouce = mfilterlist;
        //minnersouce.addAll(mfilterlist);
        notifyDataSetChanged();

    }
}
