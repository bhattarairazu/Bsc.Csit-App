package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Razu on 12/30/2017.
 */

public class Model_notice_main {
    @SerializedName("ack")
    private String ack;
    @SerializedName("ack_msg")
    private String ack_msg;
    @SerializedName("result")
    private List<Model_notice_array> result;

    public Model_notice_main(String ack, String ack_msg, List<Model_notice_array> result) {
        this.ack = ack;
        this.ack_msg = ack_msg;
        this.result = result;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getAck_msg() {
        return ack_msg;
    }

    public void setAck_msg(String ack_msg) {
        this.ack_msg = ack_msg;
    }

    public List<Model_notice_array> getResult() {
        return result;
    }

    public void setResult(List<Model_notice_array> result) {
        this.result = result;
    }
}
