package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by l24o on 17.03.16.
 */
@XmlRootElement(name = "season")
public class Season {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
