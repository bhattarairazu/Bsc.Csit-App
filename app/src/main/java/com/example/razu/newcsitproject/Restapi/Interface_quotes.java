package com.example.razu.newcsitproject.Restapi;


import com.example.razu.newcsitproject.Model.Cardview_newsource;
import com.example.razu.newcsitproject.Model.Comment_add;
import com.example.razu.newcsitproject.Model.Comment_getda;
import com.example.razu.newcsitproject.Model.Datamodel;
import com.example.razu.newcsitproject.Model.Forums_addpost;
import com.example.razu.newcsitproject.Model.Guuuu;
import com.example.razu.newcsitproject.Model.Model_downloadfiles;
import com.example.razu.newcsitproject.Model.Model_notice_main;
import com.example.razu.newcsitproject.Model.Model_syllabus_main;
import com.example.razu.newcsitproject.Model.Quotes_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Razu on 11/29/2017.
 */

public interface Interface_quotes {
    @GET("quotes/1.json")
    Call<Quotes_model> mquotes();
    @GET("getNotice?sem_id=2")
    Call<Guuuu> mq();
    @GET("sources?apiKey=27aff5efe50b433c8926e46700ab9901")
    Call<Cardview_newsource> mnewssources();

    @GET("top-headlines")
    Call<Datamodel> getTopheadlinesnews(@Query("sources") String sources, @Query("apiKey") String apikey);

    //fetching notices json datas
    //@GET("csitproject_fetchnotices.php")
    //Call<List<Data_noticeview>> getnoticedetails();
    //fetching from acepirit websites
    @GET("getNotice?sem_id=2")
    Call<Model_notice_main> mgetnotices();
    //posting forums datas
    @FormUrlEncoded
    @POST("csitproject_postdata.php")
    Call<Forums_addpost> postdata(@Field("name") String name, @Field("fbid") String fbid, @Field("gender") String gender, @Field("picurl") String picurl, @Field("commentdes") String commentdes
            , @Field("timestamps") long timestamps);

//fetching forums data
    @GET("csitproject_fetchpost.php")
     Call<List<Forums_addpost>> mgetdata();
   //posting comments
     @FormUrlEncoded
      @POST("csitproject_postcomment.php")
       Call<Comment_add> postcomment(@Field("postid") int postid, @Field("names") String names,@Field("picurl") String picurl, @Field("comment") String comment);
//getcomment
//@GET("csitproject_fetchcomment.php")
//Call<List<Comment_add>> getcommentss();
    @GET("csitproject_fetchcomment.php")
    Call<List<Comment_getda>> mccc(@Query("postid") int postid);
    //get dwoonload json fiels
    //@GET("csitproject_fechfiles.php")
    //Call<List<Model_downloadfiles>> mdownloadfiles();
    @GET("getQuestion")
    Call<Model_syllabus_main> mgetquestions(@Query("sem_id") int sem_id);
    //download first semester syllabus
    //@GET("csitproject_fetch_syllabus_firstsemester.php")
    //Call<List<Model_syllabus_firstsemester>> mdownloadsylabusfirstsem();
    //fetch syllabus from acepirit websites
    @GET("getSyllabus")
    Call<Model_syllabus_main> mgetsyllabus(@Query("sem_id") int sem_id);
//fetcg notes from acepirit website
    @GET("getNotes")
    Call<Model_syllabus_main> mgetnotes(@Query("sem_id") int sem_id);
    //fetch solution from acepirit website
    @GET("getSolution")
    Call<Model_syllabus_main> mgetsolutions(@Query("sem_id") int sem_id);
    //download first semester syllabus
    //@FormUrlEncoded
    @PUT("csitproject_updateforums.php")
    Call<Forums_addpost> mupdates(@Query("likes") int likes,@Query("postid") int postid);




}
