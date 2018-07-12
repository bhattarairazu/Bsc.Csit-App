package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 12/4/2017.
 */

public class Comment_getda {
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("names")
    @Expose
    private String names;


    public Comment_getda(String comment, String pic, String names) {
        this.comment = comment;
        this.pic = pic;
        this.names = names;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
