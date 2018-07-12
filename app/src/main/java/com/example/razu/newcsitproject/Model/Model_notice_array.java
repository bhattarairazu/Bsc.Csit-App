package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 12/30/2017.
 */

public class Model_notice_array {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    //@SerializedName("description")
    //private String description;
    @SerializedName("content")
    private String content;
    @SerializedName("date")
    private String date;

    public Model_notice_array() {
    }

    public Model_notice_array(String id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
// public String getDescription() {
    //    return description;
  //  }

    //public void setDescription(String description) {
      //  this.description = description;
    //}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
