package com.l24o.serials.parser;

import com.l24o.serials.dao.serials.ISerealsDAO;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by l24o on 18.03.16.
 */
public class NewSeriesParser {

    // link to our dao object
    private ISerealsDAO serialsDAO;

    // for serialsDAO bean property injection
    public ISerealsDAO getSerialsDAO() {
        return serialsDAO;
    }

    public void setSerialsDAO(ISerealsDAO serialsDAO) {
        this.serialsDAO = serialsDAO;
    }

    @Scheduled(fixedDelay=50000)
    public void updates(){

    }

}
