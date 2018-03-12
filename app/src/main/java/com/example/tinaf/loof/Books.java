package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Books extends Base {
    private int page;

    public int getPage(){
        return this.page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public Books(String name, String description, int page){
        super(name, description);
        this.page = page;
    }
}
