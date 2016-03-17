package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "TranslateTeam")
public class TranslateTeam {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
