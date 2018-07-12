package com.example.razu.newcsitproject.Restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Razu on 12/1/2017.
 */

public class Apiclient_source {

        public static final String BASE_URL = "https://newsapi.org/v2/";
        //public static final String BASE_URL = "http://boombox.ng";

        public static Retrofit retrofit = null;

        public static Retrofit getApiclient(){
            if(retrofit == null){
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
            }
            return retrofit;
        }



}
