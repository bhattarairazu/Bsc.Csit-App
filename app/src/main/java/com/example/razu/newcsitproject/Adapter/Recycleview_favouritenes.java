package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.razu.newcsitproject.Model.Model_favouriteslis;
import com.example.razu.newcsitproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Razu on 12/26/2017.
 */

public class Recycleview_favouritenes extends RecyclerView.Adapter<Recycleview_favouritenes.MyViewHolder> {
    List<Model_favouriteslis> mfavoure;
    private Context mcontext;

    public Recycleview_favouritenes(List<Model_favouriteslis> mfavoure, Context mcontext) {
        this.mfavoure = mfavoure;
        this.mcontext = mcontext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_newssourceitem,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.newsname.setText(mfavoure.get(position).getName());
        holder.neewsdesc.setText(mfavoure.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mfavoure.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView newsname,neewsdesc;
        ImageView newsimage,popupimage;
        public MyViewHolder(View itemView) {
            super(itemView);
            newsname = (TextView)itemView.findViewById(R.id.news_textview);
            neewsdesc = (TextView)itemView.findViewById(R.id.news_description);
            popupimage =(ImageView)itemView.findViewById(R.id.popupimage);
            popupimage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id =getAdapterPosition();
            switch (view.getId()){
                case R.id.news_description:
                    Intent intens = new Intent(view.getContext(), Detailsnewssoource.class);
                    intens.putExtra("newsname",mfavoure.get(id).getId());
                    view.getContext().startActivity(intens);
                    break;
                case R.id.news_image:
                    Intent intenss = new Intent(view.getContext(), Detailsnewssoource.class);
                    intenss.putExtra("newsname",mfavoure.get(id).getId());
                    view.getContext().startActivity(intenss);
                    break;
                case R.id.popupimage:
                    showpopupmenu(view,id);
                    break;
            }
            }
        public void showpopupmenu(View view,int id){
            PopupMenu pop = new PopupMenu(mcontext,view);
            MenuInflater mmenu = pop.getMenuInflater();
            mmenu.inflate(R.menu.menu_removefavourite,pop.getMenu());
            pop.setOnMenuItemClickListener(new MyMenuitemclicklistener(id));
            pop.show();
        }

        private class MyMenuitemclicklistener implements PopupMenu.OnMenuItemClickListener {
          int itemid;
            List<Model_favouriteslis> mfavourites = new ArrayList<>();

            public MyMenuitemclicklistener(int id) {
                this.itemid = id;


            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Database mdatabase = new Database(mcontext);
              int ids = mfavoure.get(itemid).getData_id();
                //Toast.makeText(mcontext, "Added to Favourites", Toast.LENGTH_SHORT).show();
                ////Model_favouriteslis lists = new Model_favouriteslis();
                //lists.setId(itemid);
                //mfavourites.add(lists);
                //mmanagement = new Sessionmanagement(mcontext);
                //mmanagement.insertfavourtie_news(itemid,fnmes,fdescription);
                //Model_favouriteslis mfavouriteslist = new Model_favouriteslis(itemid,fnmes,fdescription);
                boolean delete = mdatabase.remove_favourite(ids);
                if(delete){
                    Toast.makeText(mcontext, "Successfully Removed From Favourite", Toast.LENGTH_SHORT).show();
                    mfavoure.remove(itemid);
                    notifyDataSetChanged();
                    //totald.refreshDrawableState();
                }else{
                    Toast.makeText(mcontext, "Failed to delete", Toast.LENGTH_SHORT).show();
                }
                //Log.d("e", "onMenuItemClick: ,,,,,,,"+itemid+"'"+fnmes+"["+fdescription);
                return false;
            }
        }
    }
}
