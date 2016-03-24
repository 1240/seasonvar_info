package com.l24o.serials.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by l24o on 18.03.16.
 */
@XmlRootElement(name = "episode")
@Document(collection = Episode.COLLECTION_NAME)
public class Episode implements Serializable {

    public static final String COLLECTION_NAME = "episode";

    @Id
    private String code;
    private String name;
    private Date date;
    private String rating;
    private List<TranslateTeam> translateTeams;

    public List<TranslateTeam> getTranslateTeams() {
        return translateTeams;
    }

    public void setTranslateTeams(List<TranslateTeam> translateTeams) {
        this.translateTeams = translateTeams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
