import dao.serials.ISeasonvarDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

/**
 * Created by l24o on 17.03.16.
 */
public class StartParser {

    // link to our dao object
    private ISeasonvarDAO serialsDAO;

    // for serialsDAO bean property injection
    public ISeasonvarDAO getSerialsDAO() {
        return serialsDAO;
    }

    public void setSerialsDAO(ISeasonvarDAO serialsDAO) {
        this.serialsDAO = serialsDAO;
    }

    @Scheduled(fixedDelay=50000)
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
                String attr = movie.select("a").attr("href");
                System.out.println("code " + attr.split("/")[2]);
                Document serial = Jsoup.connect(String.format("%s%s", home, attr)).get();
                System.out.println("name " + serial.select("h2.page-heading").text());
                String url = serial.select("div.movie-image").select("img").attr("src");
                System.out.println(url);
                String status = serial.select("p.movie-option").get(0).select("span").text();
                String chanel = serial.select("p.movie-option").get(1).select("span").text();
                String type = serial.select("p.movie-option").get(2).select("span").text();
                String startDate = serial.select("p.movie-option").get(3).select("span").text();
                System.out.println(status);
                System.out.println(chanel);
                System.out.println(type);
                System.out.println(startDate);
                String des = serial.select("div.movie-description").select("p").text();
                System.out.println(des);
                Elements bottom = serial.select("div.movie-bottom").select("p.movie-option");
                String imdb = bottom.get(0).select("span.imdb").text();
                String tvmaze = bottom.get(0).select("span.tvmaze").text();
                String myshows = bottom.get(0).select("span.myshows").text();
                System.out.println(imdb);
                System.out.println(tvmaze);
                System.out.println(myshows);

                Elements translate = bottom.get(1).select("span");
                for (Element element : translate) {
                    System.out.println(element.text());
                }
                Elements seasons = serial.select("div.wrapper-white").select("div.panel-default");
                for (Element season : seasons) {
                    String seasonNumber = season.select("h3.panel-title").text();
                    System.out.println(seasonNumber);

                    Elements episodes = season.select("table.table").select("tr");
                    episodes.remove(0);
                    for (Element episode : episodes) {
                        Elements tds = episode.select("td");
                        String eCode = tds.get(0).text();
                        String eName = tds.get(1).text();
                        String eRating = tds.get(tds.size() - 1).text();
                        String eDate = tds.get(tds.size() - 2).text();
                        System.out.println(eCode + " " + eName + " " + eRating + " " + eDate);
                        for (Element td : tds) {
                            if (td.attr("class").equals("rg-column-td")) {
                                if (!td.text().isEmpty())
                                    System.out.println("TranslateTeam " + td.text());
                            }
                        }
                    }
                }
            }
            System.out.println("----------------------------------------------------------");
        }

    }

}
