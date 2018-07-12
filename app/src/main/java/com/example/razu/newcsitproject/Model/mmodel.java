package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 1/3/2018.
 */

public class mmodel {
    @SerializedName("ack")
    private String ack;

    public mmodel(String ack) {
        this.ack = ack;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }
}
