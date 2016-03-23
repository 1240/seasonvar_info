package com.l24o.serials.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "rating")
@Document(collection = Rating.COLLECTION_NAME)
public class Rating {
    public static final String COLLECTION_NAME = "rating";

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
