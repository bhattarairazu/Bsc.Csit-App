package com.example.razu.newcsitproject.Restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Razu on 12/1/2017.
 */

public class Apiclient_noticevie {
   // public static final String BASE_URLs = "http://demo.kulchan.com/egov/api/";
    public static final String BASE_URL = "http://demo.acepirit.com/bsccsit/api/";
   // public static String BASE_URLs ="http://demo.kulchan.com/nayashakti/api/";
    public static  Retrofit retrofit = null;
   // public static Retrofit getApiclient(){
       // if(retrofit == null){
           // retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                //    .addConverterFactory(GsonConverterFactory.create()).build();

        //}
       // return retrofit;
    //}
    public static Retrofit getApiclient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
