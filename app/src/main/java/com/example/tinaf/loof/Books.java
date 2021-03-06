package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Books extends Base {
    private String page;

    public String getPage(){
        return this.page;
    }

    public void setPage(String page){
        this.page = page;
    }

    public Books(String name, String description, String page){
        super(name, description);
        this.page = page;
    }

    public String toXml() {
        String xml = "<book> <name>" + this.getName() +
                "</name> <description>" + this.getDescription() +
                "</description> <page>" + this.getPage() +
                "</page> </book>";
        return xml;
    }
}
