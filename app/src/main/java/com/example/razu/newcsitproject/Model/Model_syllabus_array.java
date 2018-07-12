package com.example.razu.newcsitproject.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Razu on 12/30/2017.
 */

public class Model_syllabus_array {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("file")
    private String file;
    @SerializedName("sem_id")
    private String sem_id;

    public Model_syllabus_array(String id, String title, String file, String sem_id) {
        this.id = id;
        this.title = title;
        this.file = file;
        this.sem_id = sem_id;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSem_id() {
        return sem_id;
    }

    public void setSem_id(String sem_id) {
        this.sem_id = sem_id;
    }
}
