package com.example.razu.newcsitproject.Restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Razu on 11/29/2017.
 */

public class Apiclient {
    public static final String BaseURL = "http://quotes.stormconsultancy.co.uk/";
    public static Retrofit retrofit = null;
    public static Retrofit getApiclient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }

}
