package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Manga extends Base{
    private String chapter;

    public String getChapter(){
        return this.chapter;
    }

    public void setChapter(String chapter){
        this.chapter = chapter;
    }

    public Manga(String name, String description, String chapter){
        super(name, description);
        this.chapter = chapter;
    }

    public String toXml() {
        String xml = "<mangas> <name>" + this.getName() +
                "</name> <description>" + this.getDescription() +
                "</description> <chapter>" + this.getChapter() +
                "</chapter> </mangas>";
        return xml;
    }
}
