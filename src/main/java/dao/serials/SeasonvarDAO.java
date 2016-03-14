package dao.serials;

import dao.parameters.serial.SerialListParameters;
import entities.Serial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SeasonvarDAO implements ISeasonvarDAO {

    Map<String, Serial> profsMap = new HashMap<String, Serial>();

    DataSource datasource;

    private SimpleJdbcInsert insertSerial;
    private JdbcTemplate templSerial;

    public void setDataSource(DataSource dataSource) {
        this.templSerial = new JdbcTemplate(dataSource);
        this.insertSerial = new SimpleJdbcInsert(dataSource)
                .withTableName("serial");
    }

    public Serial getSerial(String id) {
        templSerial.queryForObject(String.format("SELECT * FROM serial WHERE id = '%s'", id),
                new RowMapper<Serial>() {
                    public Serial mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Serial Serial = new Serial();
                        String name = rs.getString("name");
                        String info = rs.getString("info");
                        String urlImage = rs.getString("urlImage");
                        String url = rs.getString("url");
                        int season = rs.getInt("season");
                        int seria_number = rs.getInt("seria_number");
                        String date = rs.getString("date");
                        Serial.setName(name);
                        Serial.setInfo(info);
                        Serial.setUrlImage(urlImage);
                        Serial.setUrl(url);
                        Serial.setSeason(season);
                        Serial.setSeria_number(seria_number);
                        if (!date.equals(""))
                            Serial.setDate(Date.valueOf(date));
                        return Serial;
                    }
                });
        return null;

    }

    public Serial createSerial(Serial serial) {
        if (serial != null) {
            Map<String, Object> parameters = new HashMap<String, Object>(3);
            String uuid = UUID.randomUUID().toString();
            serial.setId(uuid);
            parameters.put("id", uuid);
            if (serial.getName() != null)
                parameters.put("name", serial.getName());
            if (serial.getInfo() != null)
                parameters.put("info", serial.getInfo());
            if (serial.getUrlImage() != null)
                parameters.put("urlImage", serial.getUrlImage());
            if (serial.getUrl() != null)
                parameters.put("url", serial.getUrl());
            parameters.put("season", serial.getSeason());
            parameters.put("seria_number", serial.getSeria_number());
            parameters.put("date",
                    serial.getDate());
            insertSerial.execute(parameters);
            return serial;
        } else {
            return null;
        }
    }

    public Serial updateSerial(Serial serial) {
        /*if (serial != null && serial.getId() != null) {
            Serial oldCustomer = getSerial(serial.getId());
            String sqlUpdate = String
                    .format("UPDATE serial SET first_name = %s, last_name = %s, phone = %s, mail = %s, adress = %s, contract_id = %s, contract_expire_date = %s WHERE id = %s",
                            "'" + serial.getFirst_name() + "'", "'" + serial.getLast_name() + "'",
                            "'" + serial.getPhone() + "'", "'" + serial.getMail() + "'",
                            "'" + serial.getAdress() + "'", "'" + serial.getContract_id() + "'",
                            ((serial.getContract_expire_date() != null) ? "'" + serial.getContract_expire_date() + "'" : "null"),
                            "'" + serial.getId() + "'");
            System.out.println(sqlUpdate);
            templSerial.update(sqlUpdate);
            return oldCustomer;
        } else {
            return null;
        }*/
        return null;
    }

    public boolean removeSerial(String id) {
        /*if (templSerial
                .update("DELETE FROM serial WHERE id = '" + id + "'") > 0) {
            return true;
        } else {
            return false;
        }*/
        return false;
    }

    public List<Serial> getSerialList(SerialListParameters parameters) {
        return (List<Serial>) templSerial.query("SELECT * FROM seasonvar.serial;",
                new RowMapper<Serial>() {
                    public Serial mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Serial serial = new Serial();
                        String name = rs.getString("name");
                        String info = rs.getString("info");
                        String urlImage = rs.getString("urlImage");
                        String url = rs.getString("url");
                        int season = rs.getInt("season");
                        int seria_number = rs.getInt("seria_number");
                        String date = rs.getString("date");
                        serial.setName(name);
                        serial.setInfo(info);
                        serial.setUrlImage(urlImage);
                        serial.setUrl(url);
                        serial.setSeason(season);
                        serial.setSeria_number(seria_number);
                        if (!date.equals(""))
                            serial.setDate(Date.valueOf(date));
                        return serial;
                    }
                });
    }
}
