package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Manga extends Base{
    private int chapter;

    public int getChapter(){
        return this.chapter;
    }

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    public Manga(String name, String description, int chapter){
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
