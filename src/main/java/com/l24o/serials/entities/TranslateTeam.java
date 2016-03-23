package com.l24o.serials.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "TranslateTeam")
@Document(collection = TranslateTeam.COLLECTION_NAME )
public class TranslateTeam {

    public static final String COLLECTION_NAME = "translateteam";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
