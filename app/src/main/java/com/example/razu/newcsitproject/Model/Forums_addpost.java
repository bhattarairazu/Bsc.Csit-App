package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 12/1/2017.
 */

public class Forums_addpost {
   @SerializedName("id")
   @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fbid")
    @Expose
    private String fbid;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("picurl")
    @Expose
    private String picurl;
    @SerializedName("commentdes")
    @Expose
    private String commentdes;
    @SerializedName("timestamps")
    @Expose
    private String tiemstamps;
    @SerializedName("likeno")
    private int likeno;
    @SerializedName("commentno")
    private String commentno;
    @SerializedName("response")
    private String response;

    public Forums_addpost(int id, String name, String fbid, String gender, String picurl, String commentdes, String tiemstamps, int likeno, String commentno, String response) {
        this.id = id;
        this.name = name;
        this.fbid = fbid;
        this.gender = gender;
        this.picurl = picurl;
        this.commentdes = commentdes;
        this.tiemstamps = tiemstamps;
        this.likeno = likeno;
        this.commentno = commentno;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getCommentdes() {
        return commentdes;
    }

    public void setCommentdes(String commentdes) {
        this.commentdes = commentdes;
    }

    public String getTiemstamps() {
        return tiemstamps;
    }

    public void setTiemstamps(String tiemstamps) {
        this.tiemstamps = tiemstamps;
    }

    public int getLikeno() {
        return likeno;
    }

    public void setLikeno(int likeno) {
        this.likeno = likeno;
    }

    public String getCommentno() {
        return commentno;
    }

    public void setCommentno(String commentno) {
        this.commentno = commentno;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
