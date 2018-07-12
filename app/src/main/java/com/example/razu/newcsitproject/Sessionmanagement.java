package com.example.razu.newcsitproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.security.keystore.KeyInfo;

import com.example.razu.newcsitproject.Home.Dashboard;

import java.util.HashMap;

/**
 * Created by Razu on 12/11/2017.
 */

public class Sessionmanagement {
    //share preferance objec
    SharedPreferences pref;
    //sharedpreference editor object
    Editor editor;
    //context
    Context mcontext;
    //sharedpreference mode
    private int PRIVATE_MODE=0;
    //shared preference file name
    private static final String PREF_NAME = "BSCCSITPREF";
    //shared preference key
    private static final String KEY_VALUE="IsLoggedIn";
    private static final String KEY_VALUE_Favouritenews = "isfavourite";
    //value for storing facebook details
    public static final String KEY_NAME = "name";
    public static final String KEY_FBID="fbid";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_IMAGE="image";
    //value for storing favourite news details
    public static final String KEY_NAME_news = "fnewsname";
    public static final String KEY_ID_news="fnewsid";
    public static final String KEY__desc_news = "fnewsdesc";


    public Sessionmanagement(Context context){
        this.mcontext = context;
        pref = mcontext.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
       editor = pref.edit();
    }
    ///implementing login fucntion
    public void createloginsession(String fbid,String name,String gender,String image){
        editor.putBoolean(KEY_VALUE,true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_FBID,fbid);
        editor.putString(KEY_GENDER,gender);
        editor.putString(KEY_IMAGE,image);
        editor.commit();
    }
    public HashMap<String,String> getuserdetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        user.put(KEY_NAME,pref.getString(KEY_NAME,null));
        user.put(KEY_FBID,pref.getString(KEY_FBID,null));
        user.put(KEY_GENDER,pref.getString(KEY_GENDER,null));
        user.put(KEY_IMAGE,pref.getString(KEY_IMAGE,null ));
        return user;
    }
    //checking login
    public void checkloginstatus(){
        if (!this.isLoggedIn()){
            Intent intents = new Intent(mcontext,MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mcontext.startActivity(intents);
        }
        //if(this.isLoggedIn()){
           // Intent intents = new Intent(mcontext,Dashboard.class);
           // mcontext.startActivity(intents);

        //}

    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_VALUE, false);
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent intents = new Intent(mcontext,MainActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intents);
    }
    public void insertfavourtie_news(String ids,String namess,String desc){
        int i =0;
        editor.putInt("pid",i++);
        editor.putString(KEY_NAME_news,namess);
        editor.putString(KEY_ID_news,ids);
        editor.putString(KEY__desc_news,desc);
        editor.commit();
    }
    public HashMap<String,String> getfavouritenewsdetails(){
        HashMap<String,String> favnews = new HashMap<String,String>();
        favnews.put(KEY_NAME_news,pref.getString(KEY_NAME_news,null));
        favnews.put(KEY_ID_news,pref.getString(KEY_ID_news,null));
        favnews.put(KEY__desc_news,pref.getString(KEY__desc_news,null));
        return favnews;
    }
}
