package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/11/2018.
 */

public class Base {
    private String name;
    private String description;

    public Base(String name, String description){
        this.setName(name);
        this.setDescription(description);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
