package com.example.tinaf.loof;

import java.util.jar.Attributes;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Anime extends Base {
    private String episode;

    public String getEpisode(){
        return this.episode;
    }

    public void setEpisode(String episode){
        this.episode = episode;
    }

    public Anime(String name, String description, String episode){
        super(name, description);
        this.episode = episode;
    }

    public String toXml() {
        String xml = "<anime> <name>" + this.getName() +
                "</name> <description>" + this.getDescription() +
                "</description> <episode>" + this.getEpisode() +
                "</episode> </anime>";
        return xml;
    }
}
