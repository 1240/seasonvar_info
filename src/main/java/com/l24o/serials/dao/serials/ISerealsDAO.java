package com.l24o.serials.dao.serials;


import com.l24o.serials.dao.parameters.serial.SerialListParameters;
import com.l24o.serials.entities.Serial;

import java.util.List;

public interface ISerealsDAO {

    /*
    Serial
     */
    void createSerial(Serial serial);

    Serial getSerial(String code);

    void removeSerial(String code);

    List<Serial> getSerialList(SerialListParameters parameters);

}
