package com.example.tinaf.loof;

import java.util.jar.Attributes;

/**
 * Created by tinaf on 3/4/2018.
 */

public class Anime extends Base {
    private int episode;

    public int getEpisode(){
        return this.episode;
    }

    public void setEpisode(int episode){
        this.episode = episode;
    }

    public Anime(String name, String description, int episode){
        super(name, description);
        this.episode = episode;
    }
}
