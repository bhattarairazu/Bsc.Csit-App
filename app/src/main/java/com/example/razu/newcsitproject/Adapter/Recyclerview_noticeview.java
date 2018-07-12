package com.example.razu.newcsitproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.razu.newcsitproject.Model.Data;
import com.example.razu.newcsitproject.Home.Detailsnoticereads;
import com.example.razu.newcsitproject.Model.Model_notice_array;
import com.example.razu.newcsitproject.R;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Razu on 12/1/2017.
 */

public class Recyclerview_noticeview extends RecyclerView.Adapter<Recyclerview_noticeview.MyViewHolder> {
    private List<Model_notice_array> mnotice;
    private Context context;

    public Recyclerview_noticeview(List<Model_notice_array> mnotice, Context context) {
        this.mnotice = mnotice;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_noticeview,parent,false);

        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model_notice_array mnocit = mnotice.get(position);
       // holder.desates.setText(mnocit.getDate());

            holder.titles.setText(StringEscapeUtils.unescapeJava(mnocit.getTitle()));
          //  holder.details.setText(mnocit.getDescription());
    }

    @Override
    public int getItemCount() {
        return mnotice.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titles,details,desates;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titles = (TextView)itemView.findViewById(R.id.textview_noticetitle);
            details = (TextView)itemView.findViewById(R.id.textview_noticedetails);
            desates = (TextView)itemView.findViewById(R.id.textview_noticedates);
            details.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = getAdapterPosition();
            Intent intents = new Intent(view.getContext(), Detailsnoticereads.class);
            intents.putExtra("titles",mnotice.get(id).getTitle());
            //intents.putExtra("des",mnotice.get(id).getDescription());
            intents.putExtra("dates",mnotice.get(id).getDate());
            view.getContext().startActivity(intents);

        }
    }
    public String mdata(int post) throws UnsupportedEncodingException {
       // String myString = "\u0048\u0065\u006C\u006C\u006F World";
       // byte[] utf8Bytes = myString.getBytes("UTF8");
       // String text = new String(utf8Bytes,"UTF8");
        String mystring = mnotice.get(post).getTitle().toString();
        byte [] utf8Bytes = mystring.getBytes("UTF8");
        String text = new String(utf8Bytes,"UTF8");
        return text;
    }
   // public String mdata(int position) throws UnsupportedEncodingException {
        // String myString = "\u0048\u0065\u006C\u006C\u006F World";
        // byte[] utf8Bytes = myString.getBytes("UTF8");
        // String text = new String(utf8Bytes,"UTF8");
        //String myString = "\\u0926\\u092e\\u0915 \\u0928\\u092a\\u093e \\u0915\\u094b \\u091c\\u0930\\u0941\\u0930\\u0940 \\u0938\\u0942\\u091a\\u0928\\u093e World";
       // String myString = mnotice.get(position).get
        // byte [] utf8Bytes = myString.getBytes("UTF8");
        // String text = new String(utf8Bytes,"UTF8");
        // return text;
        //String sJava="\\u0926\\u092e\\u0915\\u0928\\u092a\\u093e\\";
       // return StringEscapeUtils.unescapeJava(myString);

}
