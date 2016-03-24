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
            for (Element movie : movies) {
                Serial serial = new Serial();
                String attr = movie.select("a").attr("href");
                serial.setCode(attr.split("/")[2]);
                Document serialDoc = Jsoup.connect(String.format("%s%s", home, attr)).get();
                serial.setName(serialDoc.select("h2.page-heading").text());
                String url = serialDoc.select("div.movie-image").select("img").attr("src");
                serial.setUrl(url);
                String status = serialDoc.select("p.movie-option").get(0).select("span").text();
                String chanel = serialDoc.select("p.movie-option").get(1).select("span").text();
                String type = serialDoc.select("p.movie-option").get(2).select("span").text();
                String startDate = serialDoc.select("p.movie-option").get(3).select("span").text();
                serial.setStatus(status);
                serial.setChanel(chanel);
                serial.setGenre(type);
                serial.setStartDate(startDate);
                String des = serialDoc.select("div.movie-description").select("p").text();
                serial.setInfo(des);
//                serialsDAO.createSerial(serial);
                Rating imdb = new Rating();
                Rating tvmaze = new Rating();
                Rating myshows = new Rating();
                Elements bottom = serialDoc.select("div.movie-bottom").select("p.movie-option");
                String imdbS = bottom.get(0).select("span.imdb").text();
                String tvmazeS = bottom.get(0).select("span.tvmaze").text();
                String myshowsS = bottom.get(0).select("span.myshows").text();
                if (!imdbS.isEmpty()) {
                    String[] imdbSplit = imdbS.replaceAll(" ", "").split(":");
                    imdb.setName(imdbSplit[0]);
                    imdb.setValue(imdbSplit[1]);
                }
                if (!tvmazeS.isEmpty()) {
                    String[] tvmazeSplit = tvmazeS.replaceAll(" ", "").split(":");
                    tvmaze.setName(tvmazeSplit[0]);
                    tvmaze.setValue(tvmazeSplit[1]);
                }
                if (!myshowsS.isEmpty()) {
                    String[] myshowsSplit = myshowsS.replaceAll(" ", "").split(":");
                    myshows.setName(myshowsSplit[0]);
                    myshows.setValue(myshowsSplit[1]);
                }
                serial.setRating(Arrays.asList(imdb, tvmaze, myshows));

                Elements translate = bottom.get(1).select("span");
                List<TranslateTeam> teams = new ArrayList<TranslateTeam>();
                for (Element element : translate) {
                    TranslateTeam team = new TranslateTeam();
                    team.setName(element.text());
                    teams.add(team);
                }
                serial.setTranslateTeam(teams);
                Elements seasonsElement = serialDoc.select("div.wrapper-white").select("div.panel-default");
                List<Season> seasons = new ArrayList<Season>();
                for (Element seasonElement : seasonsElement) {
                    String seasonNumber = seasonElement.select("h3.panel-title").text();
                    Season season = new Season();
                    Pattern p = Pattern.compile("-?\\d+");
                    Matcher m = p.matcher(seasonNumber);
                    while (m.find())
                        season.setNumber(Integer.valueOf(m.group()));
//                    serialsDAO
                    Elements episodes = seasonElement.select("table.table").select("tr");
                    episodes.remove(0);
                    List<Episode> episodesList = new ArrayList<Episode>();
                    for (Element episodeElement : episodes) {
                        Episode episode = new Episode();
                        Elements tds = episodeElement.select("td");
                        String eCode = tds.get(0).text();
                        String eName = tds.get(1).text();
                        String eRating = tds.get(tds.size() - 1).text();
                        String eDate = tds.get(tds.size() - 2).text();
                        List<TranslateTeam> translateTeams = new ArrayList<TranslateTeam>();
                        for (Element td : tds) {
                            if (td.attr("class").equals("rg-column-td")) {
                                if (!td.text().isEmpty()) {
                                    TranslateTeam translateTeam = new TranslateTeam();
                                    translateTeam.setName(td.text());
                                    translateTeams.add(translateTeam);
                                }
                            }
                        }
                        episode.setTranslateTeams(translateTeams);
                        episode.setName(eName);
                        episode.setCode(eCode);
                        episode.setRating(eRating);
                        try {
                            episode.setDate(new SimpleDateFormat("dd MMM yyyy", new Locale("ru", "RU")).parse(eDate));
                        } catch (ParseException e) {
                            episode.setDate(null);
                        }
                        episodesList.add(episode);
                    }
                    season.setEpisodes(episodesList);
                    seasons.add(season);
                }
                serial.setSeasons(seasons);
                serialRepo.save(serial);
            }
        }

    }

}
