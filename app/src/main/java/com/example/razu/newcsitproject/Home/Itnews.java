package com.example.razu.newcsitproject.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.razu.newcsitproject.Adapter.Recyclerview_news;
import com.example.razu.newcsitproject.Fragments.Fragment_favourtenews;
import com.example.razu.newcsitproject.Fragments.Fragment_news;
import com.example.razu.newcsitproject.Model.Cardview_newsource;
import com.example.razu.newcsitproject.Model.Innersource;
import com.example.razu.newcsitproject.Model.Model_favouriteslis;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient_source;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Call;


public class Itnews extends AppCompatActivity  {
    Toolbar toolbars;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView btn_back,btn_searchvies;
    EditText msearchedt;
    Interface_quotes mapiinterface;
    Recyclerview_news mrecyclerviewadapter;
    RecyclerView recyclerivies;
    RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<Innersource> mlistss= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itnews);
        toolbars =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        //recyclerivies =(RecyclerView)findViewById(R.id.recyclerviewss_news);
        setTitle("IT News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String names = getIntent().getExtras().getString("name");
        //Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
//        text_toolbars.setText(names);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
       /* mprogressbar.setVisibility(View.VISIBLE);


            mprogressbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isconnectd()) {
                    mprogressbar.setVisibility(View.GONE);
                    //mswiperefresh.setVisibility(View.VISIBLE);
                   recyclerivies.setVisibility(View.VISIBLE);
                   getnewspai();
                    }else {
                        mprogressbar.setVisibility(View.GONE);
                        recyclerivies.setVisibility(View.GONE);
                        mlinearlayout.setVisibility(View.VISIBLE);
                    }
                    }
            }, 5000);*/


        //mlayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        //recyclerivies.setLayoutManager(mlayoutmanager);
        //Log.d("0k", "onCreate: hhhhhhhhhhhhhhhhhhhh"+mlistss.toArray());

    }
    private void setupViewPager(ViewPager viewPager) {
       Itnews.ViewPagerAdapter adapter = new Itnews.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_news(), "News");
        adapter.addFragment(new Fragment_favourtenews(), "Favourite News");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            // text_toolbars.setText(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menus_news,menu);
        MenuItem m1 = menu.add(Menu.NONE,2,Menu.NONE,"Search");
        m1.setIcon(R.drawable.ic_search);
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        //MenuItem mens = menu.findItem(R.id.menu_item_searhc);
        //SearchView mserarch = (SearchView) MenuItemCompat.getActionView(mens);
        //mserarch.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_searhc:
               Intent mintent = new Intent(Itnews.this,Settings.class);
               startActivity(mintent);
              break;
        }
        return super.onOptionsItemSelected(item);
    }

/*    public boolean isconnectd(){
        ConnectivityManager mconn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo minfo =mconn.getActiveNetworkInfo();
        if(minfo != null && minfo.isConnected()){
            return true;
        }
        return false;
    }
    public void progressdialogue(String messages){
        mdialogue.setMessage(messages);
        mdialogue.setIndeterminate(false);
        mdialogue.setCancelable(false);
        mdialogue.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mdialogue.setProgress(10);
        mdialogue.show();
        Handler mahand = new Handler();
        mahand.postDelayed(new Runnable() {
            @Override
            public void run() {
                mdialogue.dismiss();
            }
        },3000);
    }
    public void getnewspai(){
        mapiinterface = Apiclient_source.getApiclient().create(Interface_quotes.class);
        Call<Cardview_newsource> mnews = mapiinterface.mnewssources();
        mnews.enqueue(new Callback<Cardview_newsource>() {
            @Override
            public void onResponse(Call<Cardview_newsource> call, Response<Cardview_newsource> response) {
                List<Innersource> minner = response.body().getSources();
                mrecyclerviewadapter = new Recyclerview_news(minner,getApplicationContext());
                recyclerivies.setAdapter(mrecyclerviewadapter);
            }

            @Override
            public void onFailure(Call<Cardview_newsource> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Innersource> arlis = new ArrayList<>();
        for(Innersource sources : mlistss ){
            String names = sources.getName().toLowerCase();
            if(names.contains(newText)){
                arlis.add(sources);
            }
            mrecyclerviewadapter.setfilter(arlis);
        }
        return true;
    }*/
}
