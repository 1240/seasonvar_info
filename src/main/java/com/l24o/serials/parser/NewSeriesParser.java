package com.l24o.serials.parser;

import com.l24o.serials.entities.Episode;
import com.l24o.serials.entities.Serial;
import com.l24o.serials.entities.TranslateTeam;
import com.l24o.serials.repo.SerialRepo;
import com.l24o.serials.utils.DateUtils;
import com.sun.jndi.toolkit.url.Uri;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by l24o on 18.03.16.
 */
public class NewSeriesParser {

    @Autowired
    private SerialRepo serialRepo;

    @Scheduled(fixedDelay = 50000)
    public void updates() throws IOException {
        String home = "http://myseries.ru/";
        Document parse = Jsoup.connect(home).get();
        Element todayE = parse.select("div.panel-default").get(0);
        Elements episodes = todayE.select("table.table").select("tr");
        episodes.remove(0);
        for (Element episodeElement : episodes) {
            Elements tds = episodeElement.select("td");
            String eCode = tds.get(0).text();
            String serialCode = tds.get(1).select("a").attr("href").split("/")[2];
            Serial serial = serialRepo.findOne(serialCode);
            List<Episode> list = serial.getSeasons().get(0).getEpisodes();
            for (Episode episode : list) {
                if (episode.getCode().equals(eCode)) {
                    return;
                }
            }
            serial = Util.serialParse(serialCode);
            serialRepo.save(serial);
        }
    }

}
