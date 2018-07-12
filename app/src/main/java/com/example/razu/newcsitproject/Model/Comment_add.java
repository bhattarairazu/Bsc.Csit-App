package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 12/1/2017.
 */

public class Comment_add {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("postid")
    @Expose
    private int postid;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("picurl")
    @Expose
    private String picurl;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("response")
    private String response;

    public Comment_add(String id, int postid, String names, String picurl, String comment, String response) {
        this.id = id;
        this.postid = postid;
        this.names = names;
        this.picurl = picurl;
        this.comment = comment;
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
