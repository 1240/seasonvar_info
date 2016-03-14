package entities;


import adapter.JaxBDateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "serial")
public class Serial extends Entity {
	/*
	 * DB table fields `id` varchar(45) NOT NULL, `first_name` varchar(45)
	 * DEFAULT NULL, `last_name` varchar(45) DEFAULT NULL, `phone` varchar(45)
	 * DEFAULT NULL, `mail` varchar(45) DEFAULT NULL, `adress` varchar(45)
	 * DEFAULT NULL, `contract_id` varchar(45) DEFAULT NULL,
	 * `contract_expire_date` date DEFAULT NULL
	 */

	private String name;
	private String info;
	private String urlImage;
	private String url;
	private Integer season;
	private Integer seria_number;
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getSeria_number() {
		return seria_number;
	}

	public void setSeria_number(Integer seria_number) {
		this.seria_number = seria_number;
	}

	@XmlJavaTypeAdapter(JaxBDateAdapter.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
