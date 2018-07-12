package com.example.razu.newcsitproject.Home;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.razu.newcsitproject.Adapter.Recyclerview_noticeview;
import com.example.razu.newcsitproject.AppConfig;
import com.example.razu.newcsitproject.JSONReadWrite;
import com.example.razu.newcsitproject.Model.Guuuu;
import com.example.razu.newcsitproject.Model.Model_notice_array;
import com.example.razu.newcsitproject.Model.Model_notice_main;
import com.example.razu.newcsitproject.Pref;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_noticevie;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Notice extends AppCompatActivity {
    private String url ="api/getNews";
    private String url2 ="api/getNotice?sem_id=2";
    private RequestQueue requestQueue ;
    private JSONReadWrite readWrite;
    Toolbar toolbars;
    TextView text_toolbars;
    ImageView btn_back;
    LinearLayout mlinear;
    ProgressBar mprogressbar;
    RecyclerView mrecycerlview;
    RecyclerView.LayoutManager mlayoutmanager;
    List<Model_notice_array> mlists = new ArrayList<>();
    Recyclerview_noticeview madapter;
    Interface_quotes mapi;
    SwipeRefreshLayout swipeRefreshLayout;
   // FloatingActionButton btns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        toolbars =(Toolbar)findViewById(R.id.ToolBars);
        requestQueue = Volley.newRequestQueue(this);
        readWrite = new JSONReadWrite(this);
        //text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
       // btn_back = (ImageView)findViewById(R.id.back_btn);
       //// btn_back.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
                //finish();

          //  }
      //  });

        setSupportActionBar(toolbars);
        setTitle("Notices");
        setTitleColor(Color.WHITE);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String names = getIntent().getExtras().getString("name");
       // Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
        //text_toolbars.setText(names);
        mlinear = (LinearLayout)findViewById(R.id.errorMessageNews);
        mprogressbar= (ProgressBar)findViewById(R.id.progressbarnotice);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_notices);
        mprogressbar.setVisibility(View.VISIBLE);

        if(isconnectd()) {
            mprogressbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mprogressbar.setVisibility(View.GONE);
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    getrestapi_noticedtas();
                }
            }, 3000);
        }else {
            mprogressbar.setVisibility(View.GONE);
            mlinear.setVisibility(View.VISIBLE);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                if(isconnectd()){
                    getrestapi_noticedtas();
                    Toast.makeText(Notice.this,"Refresh Successfull",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    mlinear.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
        //btns = (FloatingActionButton)findViewById(R.id.floatingbtn);
       // btns.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
               // Intent intents = new Intent(Notice.this,Eventadds.class);
               // startActivity(intents);
           // }
     //   });
       // RecyclerViewEmptySupport list =
                //(RecyclerViewEmptySupport)findViewById(R.id.list1);
       // list.setLayoutManager(new LinearLayoutManager(this));
        //list.setEmptyView(findViewById(R.id.list_empty));
        mrecycerlview = (RecyclerView)findViewById(R.id.recyclerview_notices);
       // mlayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //mrecycerlview.setLayoutManager(mlayoutmanager);
       // getrestapi_noticedtas();
        try {
            Log.d("p", "onCreate: nnnnnnnnnnnnnnnnnnnnnnnnnn"+mdata());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public boolean isconnectd(){
        ConnectivityManager mconn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo minfo =mconn.getActiveNetworkInfo();
        if(minfo != null && minfo.isConnected()){
            return true;
        }
        return false;
    }
  /* public void getrestapi_noticedtas(){
        mapi = Apiclient_noticevie.getApiclientss().create(Interface_quotes.class);
       Call<Model_notice_main> mdatanotice = mapi.mq();
       mdatanotice.enqueue(new Callback<Model_notice_main>)() {
           @Override
           public void onResponse(Call<List<Data_noticeview>> call, Response<List<Data_noticeview>> response) {
               mlists = response.body();
               mrecycerlview.setAdapter(new Recyclerview_noticeview(mlists,getApplicationContext()));
           }

           @Override
           public void onFailure(Call<List<Data_noticeview>> call, Throwable t) {

           }
       });
    }*/
  /* public void getrestapi_noticedtas(){
     mapi = Apiclient_noticevie.getApiclient().create(Interface_quotes.class);
     Call<Guuuu> mgu = mapi.mq();
     mgu.enqueue(new Callback<Guuuu>() {
         @Override
         public void onResponse(Call<Guuuu> call, Response<Guuuu> response) {
             String acks = response.body().getAck();
             Log.d("o", "onResponse: nnnnnnnnsssssssssss"+acks);
         }

         @Override
         public void onFailure(Call<Guuuu> call, Throwable t) {
             Log.d("oa", "onResponse: nnnnnnnnsssssssssss"+t.toString());
         }
     });
   }*/

 /*public void getrestapi_noticedtas(){
      {

          //getHeaders();
        String requestMessage = new StringRequest(Request.Method.GET, AppConfig.BASE_URL + url, new Response.Listener<String>() {
              @Override
              public void onResponse(JSONObject response) {
                  Log.v("#AboutRes", response.toString());

                  readWrite.writeFile("About.txt", response);
                  Pref.sSavePreferences(getApplicationContext(),"About.txt", true);
                  parseAndDisplay(response);




              }
          }, new com.android.volley.Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

                  try
                  {
                      if(Pref.sLoadSavedPreferencesBoolean(getApplicationContext(), "About.txt"))
                      {
                          JSONReadWrite readWrite = new JSONReadWrite(getApplicationContext());
                          parseAndDisplay( readWrite.readFile("About.txt"));
                      }

                      Log.v("Events", error.getMessage());
                      swipeRefreshLayout.post(new Runnable() {
                          @Override
                          public void run() {
                              if(swipeRefreshLayout.isRefreshing())
                              {
                                  swipeRefreshLayout.setRefreshing(false);
                              }

                              Snackbar.make(swipeRefreshLayout, "Could Not Connect To Network", Snackbar.LENGTH_LONG)
                                      .setAction("Action", null).show();

//                        Toast.makeText(getApplicationContext(), "Error Message", Toast.LENGTH_SHORT).show();
                          }
                      });
                  }catch (NullPointerException e)
                  {
                      e.printStackTrace();
                  }

              }

         // @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
          HashMap<String, String> params = new HashMap<String, String>();
          params.put("Content-Type", "application/json");
          String creds = String.format("%s:%s","kulchan","ku|ch@n453");
          String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
          params.put("Authorization", auth);
          return params;

      }
          });
          requestQueue.add(requestMessage);
  }*//*
}*/
public void getrestapi_noticedtas(){
    String request = new StringRequest(Request.Method.GET, AppConfig.BASE_URL + url, new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        }
    })
}

    public void parseAndDisplay(JSONObject response)
    {
        try
        {
            JSONArray result = response.getJSONArray("result");
            Log.v("#Result", result.toString());

            if(result.length()==0)
            {
                Snackbar.make(swipeRefreshLayout, "No Data Available", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                        return;
            }

            for(int i=0; i<result.length(); i++)
            {
                JSONObject object = result.getJSONObject(i);
               Model_notice_array events = new Model_notice_array();
                events.setDate(object.getString("date"));
                events.setId(object.getString("id"));
                //events.setContent(object.getString("content"));
                events.setTitle(object.getString("title"));
                //events.setImage(object.getString("image"));

                mlists.add(events);


            }

            Recyclerview_noticeview phoneAdapter = new Recyclerview_noticeview(mlists, getApplicationContext());
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mrecycerlview.setLayoutManager(llm);
            mrecycerlview.setAdapter(phoneAdapter);


        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }












    public String mdata() throws UnsupportedEncodingException {
        // String myString = "\u0048\u0065\u006C\u006C\u006F World";
        // byte[] utf8Bytes = myString.getBytes("UTF8");
        // String text = new String(utf8Bytes,"UTF8");
        String myString = "\\u0926\\u092e\\u0915 \\u0928\\u092a\\u093e \\u0915\\u094b \\u091c\\u0930\\u0941\\u0930\\u0940 \\u0938\\u0942\\u091a\\u0928\\u093e World";
       // byte [] utf8Bytes = myString.getBytes("UTF8");
       // String text = new String(utf8Bytes,"UTF8");
       // return text;
        //String sJava="\\u0926\\u092e\\u0915\\u0928\\u092a\\u093e\\";
        return StringEscapeUtils.unescapeJava(myString);
    }
}
