package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class TVShows extends Base{
    private String episode;

    public String getEpisode(){
        return this.episode;
    }

    public void setEpisode(String episode){
        this.episode = episode;
    }

    public TVShows(String name, String description, String episode){
        super(name, description);
        this.episode = episode;
    }

    public String toXml() {
        String xml = "<tvShows> <name>" + this.getName() +
                "</name> <description>" + this.getDescription() +
                "</description> <episode>" + this.getEpisode() +
                "</episode> </tvShows>";
        return xml;
    }
}
