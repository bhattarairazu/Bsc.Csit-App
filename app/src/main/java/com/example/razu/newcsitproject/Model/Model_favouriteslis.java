package com.example.razu.newcsitproject.Model;

/**
 * Created by Razu on 12/8/2017.
 */

public class Model_favouriteslis {
    int data_id;
    private String id;
    private String name;
    private String description;

    public Model_favouriteslis() {
    }

    public Model_favouriteslis(int data_id, String id, String name, String description) {
        this.data_id = data_id;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
