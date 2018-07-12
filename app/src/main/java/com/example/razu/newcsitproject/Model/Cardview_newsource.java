package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Razu on 12/1/2017.
 */

public class Cardview_newsource {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<Innersource> sources;

    public Cardview_newsource(String status, List<Innersource> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Innersource> getSources() {
        return sources;
    }

    public void setSources(List<Innersource> sources) {
        this.sources = sources;
    }
}
