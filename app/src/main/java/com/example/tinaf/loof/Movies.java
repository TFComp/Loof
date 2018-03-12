package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Movies extends Base{
    private String time;

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public Movies(String name, String description, String time){
        super(name, description);
        this.time = time;
    }
}
