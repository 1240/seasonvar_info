package com.l24o.serials.parser;

import com.l24o.serials.repo.SerialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by l24o on 18.03.16.
 */
public class NewSeriesParser {

    @Autowired
    private SerialRepo serialRepo;

    @Scheduled(fixedDelay = 50000)
    public void updates() {

    }

}
