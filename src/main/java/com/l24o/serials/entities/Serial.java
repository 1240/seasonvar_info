package com.l24o.serials.entities;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "serial")
@Document(collection = Serial.COLLECTION_NAME)
public class Serial implements Serializable {

    public static final String COLLECTION_NAME = "serial";

    private String code;
    private String name;
    private String url;
    private String status;
    private String chanel;
    private String genre;
    private String startDate;
    private String info;

    private List<Rating> rating;
    private List<TranslateTeam> translateTeam;
    private List<Season> seasons;

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public List<TranslateTeam> getTranslateTeam() {
        return translateTeam;
    }

    public void setTranslateTeam(List<TranslateTeam> translateTeam) {
        this.translateTeam = translateTeam;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
