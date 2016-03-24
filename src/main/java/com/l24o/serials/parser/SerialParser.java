package com.l24o.serials.parser;

import com.l24o.serials.entities.*;
import com.l24o.serials.repo.SerialRepo;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by l24o on 17.03.16.
 */
public class SerialParser {

    @Autowired
    private SerialRepo serialRepo;

        @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(fixedDelay = 5000000)
    public void update() throws IOException {
        String home = "http://myseries.ru/";
        String seriesEndPoint = "series";
        String seriesFilter = "page";
        Document parse = Jsoup.connect(String.format("%s%s?%s=%s", home, seriesEndPoint, seriesFilter, 1)).get();
        Elements elements = parse.select("div.page-info");
        String[] split = elements.get(0).text().split("из");
        Integer pages = Integer.valueOf(split[1].replace(" ", ""));
        for (int i = 1; i <= pages; i++) {
            Document doc = Jsoup.connect(String.format("%s%s?%s=%s", home, seriesEndPoint, seriesFilter, i)).get();
            Elements movies = doc.select("div.movie-about");
            List<Serial> serials = new ArrayList<Serial>();
            for (Element movie : movies) {
                String attr = movie.select("a").attr("href");
                String code = attr.split("/")[2];
                Serial serial = Util.serialParse(code);
                serials.add(serial);
            }
            serialRepo.save(serials);
        }

    }

}
