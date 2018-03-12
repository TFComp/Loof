package com.example.tinaf.loof;

/**
 * Created by tinaf on 3/4/2018.
 */

public class TVShows extends Base{
    private int episode;

    public int getEpisode(){
        return this.episode;
    }

    public void setEpisode(int episode){
        this.episode = episode;
    }

    public TVShows(String name, String description, int episode){
        super(name, description);
        this.episode = episode;
    }
}
