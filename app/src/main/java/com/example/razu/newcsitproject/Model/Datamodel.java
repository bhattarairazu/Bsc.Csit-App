package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 10/3/2017.
 */
public class Datamodel {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("articles")
   @Expose
    private List<Data> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getArticles() {
        return articles;
    }

    public void setArticles(List<Data> articles) {
        this.articles = articles;
    }
}
