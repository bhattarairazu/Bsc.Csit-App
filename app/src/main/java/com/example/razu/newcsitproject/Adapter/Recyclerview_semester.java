package com.example.razu.newcsitproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.razu.newcsitproject.Home.Dashboard;
import com.example.razu.newcsitproject.Model.Cardview_itemsimage;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Semester.Firstsem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Razu on 11/29/2017.
 */

public class Recyclerview_semester extends RecyclerView.Adapter<Recyclerview_semester.MyViewHolder> {
    private List<Cardview_itemsimage> mlist;
    private Context context;

    public Recyclerview_semester(List<Cardview_itemsimage> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewitems_semester,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         Cardview_itemsimage images = mlist.get(position);
        Picasso.with(context).load(images.getImage()).into(holder.imagevies);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagevies;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imagevies =(ImageView)itemView.findViewById(R.id.imageview_image_sem);
            imagevies.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = getAdapterPosition();
            final String name_first="First Semester";
            final String name_second="Second Semester";
            final String name_third="Third Semester";
            Log.d("ids", "onClick: ddddddddddddd"+id);
            switch (id){
                case 0:
                    Intent intents = new Intent(view.getContext(), Firstsem.class);
                    intents.putExtra("name",name_first);
                    view.getContext().startActivity(intents);
                    break;
                case 1:
                    Intent intents1 = new Intent(view.getContext(), Firstsem.class);
                    intents1.putExtra("name",name_second);
                    view.getContext().startActivity(intents1);
                    break;
                case 2:
                    Intent intents2 = new Intent(view.getContext(), Firstsem.class);
                    intents2.putExtra("name",name_third);
                    view.getContext().startActivity(intents2);
                    break;
                case 3:
                   // Intent intents3 = new Intent(view.getContext(), Firstsem.class);
                    //view.getContext().startActivity(intents3);
                    final AlertDialog mdialogs = new AlertDialog.Builder(view.getContext()).create();

                    mdialogs.setTitle("4th Sem");
                    mdialogs.setMessage("Comming Soon....");
                    mdialogs.setIcon(R.drawable.bell);
                    mdialogs.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mdialogs.dismiss();
                        }
                    });
                    mdialogs.show();
                    break;
                case 4:
                    //Intent intents4 = new Intent(view.getContext(), Firstsem.class);
                   // view.getContext().startActivity(intents4);
                    final AlertDialog mdialog1 = new AlertDialog.Builder(view.getContext()).create();

                    mdialog1.setTitle("5th Sem ");
                    mdialog1.setMessage("Comming Soon....");
                    mdialog1.setIcon(R.drawable.bell);
                    mdialog1.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mdialog1.dismiss();
                        }
                    });
                    mdialog1.show();
                    break;
                case 5:
                    final AlertDialog mdialog2 = new AlertDialog.Builder(view.getContext()).create();

                    mdialog2.setTitle("6th Sem");
                    mdialog2.setMessage("Comming Soon....");
                    mdialog2.setIcon(R.drawable.bell);
                    mdialog2.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mdialog2.dismiss();
                        }
                    });
                    mdialog2.show();
                    break;
                case 6:
                    final AlertDialog mdialog3 = new AlertDialog.Builder(view.getContext()).create();

                    mdialog3.setTitle("7th Sem");
                    mdialog3.setMessage("Comming Soon....");
                    mdialog3.setIcon(R.drawable.bell);
                    mdialog3.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mdialog3.dismiss();
                        }
                    });
                    mdialog3.show();
                    break;
                case 7:
                    final AlertDialog mdialog4 = new AlertDialog.Builder(view.getContext()).create();

                    mdialog4.setTitle("8th Sem ");
                    mdialog4.setMessage("Comming Soon....");
                    mdialog4.setIcon(R.drawable.bell);
                    mdialog4.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mdialog4.dismiss();
                        }
                    });
                    mdialog4.show();
                    break;


            }

        }
    }
}
