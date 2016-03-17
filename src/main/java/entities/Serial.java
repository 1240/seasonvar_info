package entities;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "serial")
public class Serial {
    /*
	 * DB table fields `id` varchar(45) NOT NULL, `first_name` varchar(45)
	 * DEFAULT NULL, `last_name` varchar(45) DEFAULT NULL, `phone` varchar(45)
	 * DEFAULT NULL, `mail` varchar(45) DEFAULT NULL, `adress` varchar(45)
	 * DEFAULT NULL, `contract_id` varchar(45) DEFAULT NULL,
	 * `contract_expire_date` date DEFAULT NULL
	 */

    private String code;
    private String name;
    private String url;
    private String status;
    private String chanel;
    private String genre;
    private String startDate;
    private String info;

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
