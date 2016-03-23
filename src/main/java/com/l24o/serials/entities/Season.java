package com.l24o.serials.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "season")
@Document(collection = Season.COLLECTION_NAME)
public class Season {
    public static final String COLLECTION_NAME = "season";

    private int number;
    private List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
