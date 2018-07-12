package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.razu.newcsitproject.Home.Comment_detials;
import com.example.razu.newcsitproject.Model.Forums_addpost;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Razu on 12/1/2017.
 */

public class Recyclerview_forumsview extends RecyclerView.Adapter<Recyclerview_forumsview.MyViewHolder> {
    private List<Forums_addpost> mdatalist;
    private Context conext;
    String names_id,pic_u;
    int  like_no;
    Interface_quotes minterface;


    public Recyclerview_forumsview(List<Forums_addpost> mdatalist, Context conext, String names_id) {
        this.mdatalist = mdatalist;
        this.conext = conext;
        this.names_id = names_id;
    }

    public Recyclerview_forumsview(List<Forums_addpost> mdatalist, Context conext, String names_id, String pic_u) {
        this.mdatalist = mdatalist;
        this.conext = conext;
        this.names_id = names_id;
        this.pic_u = pic_u;
    }

    public Recyclerview_forumsview(List<Forums_addpost> mdatalist, Context conext) {
        this.mdatalist = mdatalist;
        this.conext = conext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_viewpost,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     Forums_addpost madds = mdatalist.get(position);
     holder.names.setText(madds.getName());
     holder.post.setText(madds.getCommentdes());
     Picasso.with(conext).load(madds.getPicurl()).into(holder.fbimages);
      holder.timestamps.setText(convertedtime(position));
     // like_no = madds.getLikeno();
      holder.plike.setText(Integer.toString(madds.getLikeno()));
      holder.pcomment.setText(madds.getCommentno());
    }

    @Override
    public int getItemCount() {
        return mdatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView fbimages,postlike_img,postcomment_img;
        TextView names,post,timestamps,plike,pcomment;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            fbimages = (ImageView)itemView.findViewById(R.id.profile_image);
            names = (TextView)itemView.findViewById(R.id.text_profilename);
            post = (TextView)itemView.findViewById(R.id.postdescription);
            post.setOnClickListener(this);
            names.setOnClickListener(this);
            timestamps =(TextView)itemView.findViewById(R.id.timestamp_text);
            plike =(TextView)itemView.findViewById(R.id.likeOfPost);
            postlike_img=(ImageView)itemView.findViewById(R.id.image_likeofpost);
            postlike_img.setOnClickListener(this);
            pcomment =(TextView)itemView.findViewById(R.id.commentOfPost);
            postcomment_img=(ImageView)itemView.findViewById(R.id.img_comment_ofpost);

        }

        @Override
        public void onClick(View view) {
            like_no = mdatalist.get(getAdapterPosition()).getLikeno();
            int ids = mdatalist.get(getAdapterPosition()).getId();

            switch (view.getId()){
                case R.id.image_likeofpost:
                   ///increment(like_no);
                    like_no++;
                   // ad =ad+1;
                    minterface = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
                    Call<Forums_addpost> muppost = minterface.mupdates(like_no,ids);
                    muppost.enqueue(new Callback<Forums_addpost>() {
                        @Override
                        public void onResponse(Call<Forums_addpost> call, Response<Forums_addpost> response) {
                           Forums_addpost mpost = response.body();
                            Toast.makeText(conext,mpost.getResponse(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Forums_addpost> call, Throwable t) {
                            Log.d("failed", "onFailurLure:34df"+t.toString());
                        }
                    });
                    Log.d("plike", "onClick: nnnnnnnnnnnnnnnnnnnnnnnnn"+like_no+"id"+ids);
                    break;
                case R.id.postdescription:
                    Intent intens = new Intent(view.getContext(), Comment_detials.class);
                    intens.putExtra("idn",mdatalist.get(getAdapterPosition()).getId());
                    intens.putExtra("names",mdatalist.get(getAdapterPosition()).getName());
                    intens.putExtra("post",mdatalist.get(getAdapterPosition()).getCommentdes());
                    intens.putExtra("names_id",names_id);
                    intens.putExtra("picurl",mdatalist.get(getAdapterPosition()).getPicurl());
                    intens.putExtra("ppic",pic_u);
                    view.getContext().startActivity(intens);
                    break;

            }

        }
    }

    public String convertedtime(int position){
        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;
        int month = 30 * DAY_MILLIS;

        long currenttime=getcurrentimemilli();
        //String curtiem = Long.toString(currenttime);
        String oldtime = mdatalist.get(position).getTiemstamps();
        if(oldtime.equals("")){
            long a = 0;
            oldtime=Long.toString(a);
        }
        long oldtimes = Long.parseLong(oldtime);
        long timedif = currenttime - oldtimes;
        //long seconds=(timedif/1000)%60;

        //long minutes=((timedif/1000)-seconds)/60;
        //long hour = minutes/60;
        //String ago =  +hour +" hour ago";
        String stamp;

        if (timedif < MINUTE_MILLIS) {
            stamp= "just now";
            // return stamp;
        } else if (timedif < 2 * MINUTE_MILLIS) {
            stamp= "a minute ago";
            // return stamp;
        } else if (timedif < 50 * MINUTE_MILLIS) {
            stamp =  timedif / MINUTE_MILLIS + " minutes ago";
            /// return stamp;
        } else if (timedif < 90 * MINUTE_MILLIS) {
            stamp = "an hour ago";
            //return stamp;
        } else if (timedif < 24 * HOUR_MILLIS) {
            stamp= timedif / HOUR_MILLIS + " hours ago";
            //return stamp;
        } else if (timedif < 48 * HOUR_MILLIS) {
            stamp= "yesterday";
            //return stamp;
        } else {//if (timedif < 144 * HOUR_MILLIS ) {
            stamp = timedif / DAY_MILLIS + " days ago";
        }//return stamp;}
        //}else {
        // stamp = timedif / month + " month ago";
        //}

        return stamp;
    }
    public long getcurrentimemilli(){
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        long currenttime = dates(date);
        //String curtime =currenttime
        return currenttime;
    }
    public long dates(String datess){
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        try {

            //String date = df.format(Calendar.getInstance().getTime());
            Date mDate = df.parse(datess);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);

            //long seconds=(timeInMilliseconds/1000)%60;

            //long minutes=((timeInMilliseconds-seconds)/1000)/60;
            //long hour = minutes/60;
            //Log.d("date", "onCreate: tiiiiiiiiiiiiiiiiiiiiii"+minutes+"/"+seconds+"/"+hour);
            return timeInMilliseconds;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
