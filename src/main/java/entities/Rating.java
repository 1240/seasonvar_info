package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "rating")
public class Rating {

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
