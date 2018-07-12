package com.example.razu.newcsitproject.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.razu.newcsitproject.Model.Quotes_model;
import com.example.razu.newcsitproject.R;
import com.example.razu.newcsitproject.Restapi.Apiclient;
import com.example.razu.newcsitproject.Restapi.Interface_quotes;
import com.example.razu.newcsitproject.Sessionmanagement;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.razu.myapplication.R;

public class Dashboard extends AppCompatActivity {
Toolbar toolbars,tol_colpaitis;
TextView text_toolbars,quotes_textview,author_author;
Interface_quotes quotesinterface;
ImageButton mprofile,msemester,mroutine,mnotice,mnews,mforums,mfedbacks;
Sessionmanagement msession;
    String userid,genders,photos,names,emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.content_collapsing);
        msession = new Sessionmanagement(this);
        msession.checkloginstatus();
        userid = getIntent().getExtras().getString("appid");
        names = getIntent().getExtras().getString("name");
       genders = getIntent().getExtras().getString("gender");
        photos = getIntent().getExtras().getString("image");
        if(userid==null && names==null && genders == null && photos == null){
            userid="0";
            names="Names";
            genders="Gender";
            photos="photos";
        }
        toolbars =(Toolbar)findViewById(R.id.ToolBars);

        //tol_colpaitis = (Toolbar)findViewById(R.id.toolbar_collapsings);
        setSupportActionBar(toolbars);
        setTitle("Dashboard");
        //quotes_textview =(TextView)findViewById(R.id.textview_quotes);
        //author_author =(TextView)findViewById(R.id.textview_author);

        text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
//image button casting views

        mprofile = (ImageButton)findViewById(R.id.imgbtn_profile);
        mfedbacks = (ImageButton)findViewById(R.id.imgbtn_feedback);
        mforums = (ImageButton)findViewById(R.id.imgbtn_forum);
        mnews = (ImageButton)findViewById(R.id.imgbtn_news);
        mnotice = (ImageButton)findViewById(R.id.imgbtn_notice);
        mroutine = (ImageButton)findViewById(R.id.imgbtn_routine);
        msemester = (ImageButton)findViewById(R.id.imgbtn_semester);

        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt="Profile";
                Intent intent = new Intent(Dashboard.this,Profile.class);
                intent.putExtra("name",tt);
                startActivity(intent);
            }
        });
        mfedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AlertDialog.Builder malert = new AlertDialog.Builder(Dashboard.this);
              //  View views =getLayoutInflater().inflate(R.layout.upload_files,null);
               // malert.setView(views);
               // final AlertDialog mdialogs = malert.create();
               // mdialogs.show();
                Intent intentst_csitforums = new Intent(Dashboard.this,Feedback.class);
                startActivity(intentst_csitforums);

            }
        });
        mforums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt_csitforums="CSIT Forums";
                Intent intent_csitforums = new Intent(Dashboard.this,Csitforums.class);
                intent_csitforums.putExtra("name_toolbar",tt_csitforums);
                intent_csitforums.putExtra("names",names);
                intent_csitforums.putExtra("gender",genders);
                intent_csitforums.putExtra("ids",userid);
                intent_csitforums.putExtra("picurl",photos);

                startActivity(intent_csitforums);
            }
        });
        mnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt_itnews="IT News";
                Intent intent_itnews = new Intent(Dashboard.this,Itnews.class);
                intent_itnews.putExtra("name",tt_itnews);
                startActivity(intent_itnews);
            }
        });
        mnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt_notice="Notice";
                Intent intent_notice = new Intent(Dashboard.this,Notice.class);
                intent_notice.putExtra("name",tt_notice);
                startActivity(intent_notice);
            }
        });
        mroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt_csit="Routine";
                Intent intent_csit = new Intent(Dashboard.this,Csit_colleges.class);
                intent_csit.putExtra("name",tt_csit);
                startActivity(intent_csit);
            }
        });
        msemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tt_semester="Semester";
                Intent intent_semester = new Intent(Dashboard.this,Semester.class);
                intent_semester.putExtra("name",tt_semester);
                startActivity(intent_semester);
            }
        });





        //new DrawerBuilder().withActivity(this).build();
        // Create the AccountHeader

        //drawer image loader
        //yee vanda talako comment hataune
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {

            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
            });



            AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(names).withEmail(genders).withIcon(photos)
                )
               .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                   }
                })
                .build();

//*/
//drawer image loader

        //adding drawer items

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_dashboaard);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_profile);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName(R.string.drawer_semester);
       // SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(4).withName(R.string.drawer_csitcolleges);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5).withName(R.string.drawer_notice);
       SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_routine);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName(R.string.drawer_itnes);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(8).withName(R.string.drawer_jobs);
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(9).withName(R.string.drawer_csitforums);
      //  SecondaryDrawerItem item0 = new SecondaryDrawerItem().withIdentifier(0).withName(R.string.drawer_setting);
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(10).withName(R.string.drawer_feedback);
      //  SecondaryDrawerItem item11 = new SecondaryDrawerItem().withIdentifier(11).withName(R.string.drawer_faq);
       final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbars)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1.withIcon(R.drawable.ic_dashboard),
                        new DividerDrawerItem(),
                        item2.withIcon(R.drawable.ic_profile),
                        item3.withIcon(R.drawable.ic_semester),
                        //item4.withIcon(R.drawable.ic_colleges),
                        item5.withIcon(R.drawable.ic_notice),
                        item6.withIcon(R.drawable.ic_library),
                        item7.withIcon(R.drawable.ic_news),
                        item8.withIcon(R.drawable.ic_forum),
                        new DividerDrawerItem(),
                       item9.withIcon(R.drawable.ic_forum),
                        item10.withIcon(R.drawable.ic_feedback)
                        //item11.withIcon(R.drawable.ic_faq)
                       // new SecondaryDrawerItem().withName(R.string.drawer_profile)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

              switch (position){
                  case 1:
                      text_toolbars.setText(R.string.drawer_dashboaard);

                      break;
                  case 3:

                      //text_toolbars.setText(R.string.drawer_profile);
                      final String tt="Profile";
                      Intent intent = new Intent(Dashboard.this,Profile.class);
                      intent.putExtra("name",tt);
                      startActivity(intent);

                      break;
                  case 4:
                      //text_toolbars.setText(R.string.drawer_semester);
                      final String tt_semester="Semester";
                      Intent intent_semester = new Intent(Dashboard.this,Semester.class);
                      intent_semester.putExtra("name",tt_semester);
                      startActivity(intent_semester);
                      break;
                  case 6:
                      //text_toolbars.setText(R.string.drawer_csitcolleges);
                      final String tt_csit="Routine";
                      Intent intent_csit = new Intent(Dashboard.this,Csit_colleges.class);
                      intent_csit.putExtra("name",tt_csit);
                      startActivity(intent_csit);
                      break;
                  case 5:
                      //text_toolbars.setText(R.string.drawer_notice);
                      final String tt_notice="Notice";
                      Intent intent_notice = new Intent(Dashboard.this,Notice.class);
                      intent_notice.putExtra("name",tt_notice);
                      startActivity(intent_notice);
                      break;
                      /*
                  case 7:
                      //text_toolbars.setText(R.string.drawer_elibrary);
                      final String tt_libraray="e-Library";
                      Intent intent_library = new Intent(Dashboard.this,Elibraray.class);
                      intent_library.putExtra("name",tt_libraray);
                      startActivity(intent_library);
                      break;*/
                  case 7:
                      //text_toolbars.setText(R.string.drawer_itnes);
                      final String tt_itnews="IT News";
                      Intent intent_itnews = new Intent(Dashboard.this,Itnews.class);
                      intent_itnews.putExtra("name",tt_itnews);
                      startActivity(intent_itnews);
                      break;
                  case 8:
                      //text_toolbars.setText(R.string.drawer_itnes);
                      final String tt_jobs="IT Jobs";
                      //Intent intent_Intent1s = new Intent(Dashboard.this,Jobs.class);
                      //intent_Intent1s.putExtra("name",tt_jobs);
                      //startActivity(intent_Intent1s);
                      final AlertDialog mdialog = new AlertDialog.Builder(Dashboard.this).create();

                              mdialog.setTitle("IT JOBS");
                              mdialog.setMessage("Comming Soon....");
                              mdialog.setIcon(R.drawable.bell);
                              mdialog.setButton("OK", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      mdialog.dismiss();
                                  }
                              });
                              mdialog.show();

                      break;
                  case 10:
                      //text_toolbars.setText(R.string.drawer_csitforums);
                      final String tt_csitforums="CSIT Forums";
                      Intent intent_csitforums = new Intent(Dashboard.this,Csitforums.class);
                      intent_csitforums.putExtra("name_toolbar",tt_csitforums);
                      intent_csitforums.putExtra("names",names);
                      intent_csitforums.putExtra("gender",genders);
                      intent_csitforums.putExtra("ids",userid);
                      intent_csitforums.putExtra("picurl",photos);

                      startActivity(intent_csitforums);
                      break;/*
                  case 11:
                      //text_toolbars.setText(R.string.drawer_setting);
                      final String tt_settings="Setting";
                      Intent intent_settings = new Intent(Dashboard.this,Settings.class);
                      intent_settings.putExtra("name",tt_settings);
                      startActivity(intent_settings);
                      break;*/
                  case 11:
                      //text_toolbars.setText(R.string.drawer_feedback);
                     // final String tt_feedback="Feedback";
                      //Intent intent_feedback = new Intent(Dashboard.this,Feedback.class);
                      //intent_feedback.putExtra("name",tt_feedback);
                      //startActivity(intent_feedback);
                      AlertDialog.Builder malert = new AlertDialog.Builder(Dashboard.this);
                      View views =getLayoutInflater().inflate(R.layout.activity_feedback,null);
                      malert.setView(views);
                      final AlertDialog mdialogs = malert.create();
                      mdialogs.show();

                      break;
                 default:
                      //text_toolbars.setText(R.string.drawer_faq);
                      //final String tt_faq="FAQs";
                      //Intent intent_faq = new Intent(Dashboard.this,Faq.class);
                      //intent_faq.putExtra("name",tt_faq);
                      //startActivity(intent_faq);
                      disconnectFromFacebook();
                      msession.logout();
                     toastmessage("Successfully logout");
                      finish();

                      //*/


              }
                        //result.closeDrawer();

                        return false;
                    }
                })
                .build();
        //item1.withIcon(R.drawable.ic_dashboard);
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.ic_logout));
        //initCollapsingToolbar();
        //restia();


    }

    public void restpia(){
        quotesinterface = Apiclient.getApiclient().create(Interface_quotes.class);
        Call<Quotes_model> mclal = quotesinterface.mquotes();
        mclal.enqueue(new Callback<Quotes_model>() {
            @Override
            public void onResponse(Call<Quotes_model> call, Response<Quotes_model> response) {
                String quotes = response.body().getQuote();

                String authors = response.body().getAuthor();
                Log.d("teriva", "onResponse: hhhhhhhhhhhhhh"+quotes+authors);
                quotes_textview.setText(quotes);
                author_author.setText(authors);
            }

            @Override
            public void onFailure(Call<Quotes_model> call, Throwable t) {

            }
        });
    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();


            }
        }).executeAsync();
    }
   public void toastmessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


}
